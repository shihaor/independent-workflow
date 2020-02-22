package com.sdt.modeler.controller;

import com.sdt.JsonUtil;
import com.sdt.modeler.service.OperatorModelService;
import com.sdt.modeler.vo.ModelVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @RequestMapping("/index")
    public String index(Model model) {
        List<org.activiti.engine.repository.Model> resultList = operatorModelService.findAllModel();
        model.addAttribute("modelList", resultList);
        return "index.html";
    }

    @GetMapping("/editor")
    public String editor() {
        return "modeler.html";
    }

    @ResponseBody
    @GetMapping(value = "/getModelId")
    public String getModelId() throws Exception {
        String modelId = operatorModelService.getModelId();
        return JsonUtil.genJsonSuccess(modelId);
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
