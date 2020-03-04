package com.sdt.modeler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.modeler.service.OperatorModelService;
import com.sdt.modeler.vo.ModelVO;
import com.sdt.common.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * 操作模型的接口
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@Controller
public class OperatorModelController {

    @Resource
    private OperatorModelService operatorModelService;

    /**
     * 不加produces前端layui解析JSON乱码
     *
     * @return 模型数据
     * @throws JsonProcessingException json转化错误
     */
    @ResponseBody
    @GetMapping(value = "/listModel", produces = "application/json;charset=UTF-8")
    public String listModel() throws JsonProcessingException {
        List<org.activiti.engine.repository.Model> resultList = operatorModelService.findAllModel();
        return JsonUtil.genJsonList(resultList);
    }

    @GetMapping("/modelIdList")
    public String modelIdList(Model model) {
        List<ModelVO> modelIdList = operatorModelService.findAllModelIdList();
        model.addAttribute("list", modelIdList);
        return "pngList.html";
    }

    @GetMapping("/getPng/{id}")
    public void pngList(@PathVariable("id") String modelId, HttpServletResponse response) throws IOException {
        byte[] png = operatorModelService.getPng(modelId);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(png);
            outputStream.flush();
        }
    }

    @GetMapping("/editor")
    public String editor() {
        return "modeler.html";
    }

    @GetMapping(value = "/getModelId")
    public String getModelId(Model model) throws Exception {
        String modelId = operatorModelService.getModelId();
        model.addAttribute("modelId", modelId);
        return "addModel.html";
    }

    @ResponseBody
    @RequestMapping(value = "/createModel", method = RequestMethod.POST)
    public String createModel(@RequestBody ModelVO modelVO) throws Exception {

        String name = modelVO.getName();
        String key = modelVO.getKey();
        String modelId = modelVO.getModelId();
        operatorModelService.createModel(name, key, modelId);
        return JsonUtil.genJsonSuccess("创建成功");
    }

    @ResponseBody
    @RequestMapping("/publishModel")
    public String publishModel(String modelId) throws Exception {
        operatorModelService.publishModel(modelId);
        return JsonUtil.genJsonSuccess("publish model success");
    }

    @ResponseBody
    @RequestMapping("/revokePublish")
    public String revokePublish(String modelId) throws Exception {

        operatorModelService.revokePublish(modelId);
        return JsonUtil.genJsonSuccess("revoke model success");
    }

    @ResponseBody
    @RequestMapping("/deleteModel")
    public String deleteModel(String modelId) throws Exception {

        operatorModelService.deleteModel(modelId);
        return JsonUtil.genJsonSuccess("delete model success");
    }

    @ResponseBody
    @RequestMapping("/downloadModel")
    public void downloadModel(HttpServletResponse response, String modelId) throws IOException {
        byte[] bytes = operatorModelService.downloadModel(modelId);
        String filename = new Date().toString() + ".bpmn20.xml";
        response.setContentType("application/x-msdownload;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"");
        PrintWriter outputStream = response.getWriter();
        outputStream.print(new String(bytes, StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
