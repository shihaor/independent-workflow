package com.sdt.modeler.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author shihaoran
 * @date 2020/1/31
 */
@Service
@Scope("prototype")
@Transactional(rollbackOn = Exception.class)
public class OperatorModelService {

    @Resource
    private RepositoryService repositoryService;

    private final Logger LOGGER = LoggerFactory.getLogger(OperatorModelService.class);

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取所有的模型
     *
     * @return 所有模型的集合
     */
    public List<Model> findAllModel() {
        return repositoryService.createModelQuery().list();
    }

    /**
     * 给前端返回一个modelId供使用
     */
    public String getModelId() {
        Model model = repositoryService.newModel();
        return model.getId();
    }

    /**
     * 创建一个模型
     *
     * @param modelId 模型的id
     * @param name    模型的名字
     * @param key     模型的key，可以作为唯一性判断
     */
    public void createModel(String name, String key, String modelId) {
        LOGGER.info("创建模型入参name：{},key:{}", name, key);
        Model model = repositoryService.getModel(modelId);
        if (null != model) {
            ObjectNode modelNode = mapper.createObjectNode();
            modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "");
            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            model.setName(name);
            model.setKey(key);
            model.setMetaInfo(modelNode.toString());
            repositoryService.saveModel(model);
            createObjectNode(modelId);
        }
    }

    /**
     * 创建模型时完善ModelEditorSource
     *
     * @param modelId 模型Id
     */
    private void createObjectNode(String modelId) {
        try {
            LOGGER.info("创建模型完善ModelEditorSource入参模型ID：{}", modelId);
            ObjectNode editorNode = mapper.createObjectNode();
            editorNode.put("id", "shr");
            editorNode.put("resourceId", "shr");
            ObjectNode stencilSetNode = mapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            repositoryService.addModelEditorSource(modelId, editorNode.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOGGER.error("创建模型时完善ModelEditorSource服务异常：{}", e);
            throw new ActivitiException("save model editor sources fail");
        }
        LOGGER.info("创建模型完善ModelEditorSource结束");
    }

    /**
     * 通过模型id发布
     *
     * @param modelId 模型Id
     */
    public void publishModel(String modelId) throws IOException {
        LOGGER.info("流程部署入参modelId：{}",modelId);
        Model modelData = repositoryService.getModel(modelId);
        byte[] source = repositoryService.getModelEditorSource(modelData.getId());
        if (null == source) {
            LOGGER.info("部署ID:{}的模型数据为空，请先设计流程并成功保存，再进行发布",modelId);
            throw new ActivitiException("the resources is null, pleasse check it");
        }
        JsonNode modelNode = new ObjectMapper().readTree(source);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        // 这里可以调用项目中的部署方法
        Deployment deploy = repositoryService.createDeployment().name(modelData.getName()).addBpmnModel(modelData.getKey()
                + ".bpmn20.xml", model).deploy();
        modelData.setDeploymentId(deploy.getId());
        repositoryService.saveModel(modelData);
    }
}
