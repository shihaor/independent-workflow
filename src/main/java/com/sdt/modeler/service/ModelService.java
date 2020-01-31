package com.sdt.modeler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sdt.modeler.controller.ModelController;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.activiti.editor.constants.ModelDataJsonConstants.*;

/**
 * {@link com.sdt.modeler.controller.ModelController} 的服务类
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@Service
@Scope("prototype")
@Transactional(rollbackOn = Exception.class)
public class ModelService {

    @Resource
    private RepositoryService repositoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelService.class);

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取model的节点信息，编辑器根据返回的json进行绘图
     *
     * @param modelId 模型的id
     * @return 节点数据
     */
    public ObjectNode getEditJson(String modelId) {
        // 创建节点
        ObjectNode modelNode = null;
        // 从库中获取模型
        Model model = repositoryService.getModel(modelId);
        if (null != model) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    modelNode = (ObjectNode) mapper.readTree(model.getMetaInfo());
                } else {
                    modelNode = mapper.createObjectNode();
                    modelNode.put(MODEL_NAME, model.getName());
                }
                modelNode.put(MODEL_ID, model.getId());
                byte[] source = repositoryService.getModelEditorSource(model.getId());
                ObjectNode editorJsonNode = (ObjectNode) mapper.readTree(new String(source, StandardCharsets.UTF_8));
                modelNode.put("model", editorJsonNode);
            } catch (Exception e) {
                LOGGER.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        return modelNode;
    }

    /**
     * 保存构建好的模型
     *
     * @param modelId     模型ID
     * @param name        模型名字
     * @param description 模型描述
     * @param json_xml    模型json数据
     * @param svg_xml     图片信息
     */
    public void saveModel(String modelId, String name, String description, String json_xml, String svg_xml) {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ByteArrayInputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes(StandardCharsets.UTF_8))) {
            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) mapper.readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, model.getName());
            modelJson.put(MODEL_DESCRIPTION, description);

            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            // Saves the model. If the model already existed, the model is updated otherwise a new model is created.
            repositoryService.saveModel(model);
            // Saves the model editor source for a model
            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes(StandardCharsets.UTF_8));

            TranscoderInput input = new TranscoderInput(svgStream);
            PNGTranscoder transcoder = new PNGTranscoder();
            TranscoderOutput output = new TranscoderOutput(outputStream);
            // 将svg转换为PNG
            transcoder.transcode(input, output);
            // 获取图片信息
            final byte[] result = outputStream.toByteArray();
            // Saves the model editor source extra for a model
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
        } catch (Exception e) {
            LOGGER.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }

    /**
     * 获取编辑页面的样式
     *
     * @return
     */
    public String getStencilet() {

        try {
            InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            LOGGER.error("the file 'stencilset' is not exist");
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}
