package com.sdt.modeler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdt.JsonUtil;
import com.sdt.modeler.service.OperatorModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作模型的接口
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@Controller
@RequestMapping("/operator")
public class OperatorModelController {

    @Resource
    private OperatorModelService operatorModelService;

    @RequestMapping("/index")
    public String index(Model model) {
        List<org.activiti.engine.repository.Model> resultList = operatorModelService.findAllModel();
        model.addAttribute("modelList", resultList);
        return "index.html";
    }

    @GetMapping("editor")
    public String editor() {
        return "modeler";
    }

    @ResponseBody
    @RequestMapping("/getModelId")
    public String getModelId(Model model) throws Exception {
        String modelId = operatorModelService.getModelId();
        return JsonUtil.genJsonSuccess(modelId);
    }

    @RequestMapping("/createModel")
    public void createModel(HttpServletResponse response, String name, String key, String modelId) {
        operatorModelService.createModel(name, key, modelId);
    }

    @ResponseBody
    @RequestMapping("/publishModel")
    public String publishModel(String modelId) throws Exception {
        operatorModelService.publishModel(modelId);
        return JsonUtil.genJsonSuccess("发布成功");
    }

}
