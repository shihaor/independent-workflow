package com.sdt.modeler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.bean.PagerBean;
import com.sdt.common.utils.JsonUtil;
import com.sdt.common.utils.PageUtil;
import com.sdt.modeler.service.OperatorModelService;
import com.sdt.modeler.vo.ModelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "操作模型的接口", tags = "操作模型的接口")
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
    @ApiOperation(value = "列出全部的model")
    @GetMapping(value = "/listModel", produces = "application/json;charset=UTF-8")
    public String listModel(PagerBean pagerBean) throws Exception {
        List<org.activiti.engine.repository.Model> resultList = operatorModelService.findAllModel();
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }

    @GetMapping("/modelIdList")
    @ApiOperation(value = "列出全部的modelId")
    public String modelIdList(Model model) {
        List<ModelVO> modelIdList = operatorModelService.findAllModelIdList();
        model.addAttribute("list", modelIdList);
        return "pngList.html";
    }

    @GetMapping("/getPng/{id}")
    @ApiOperation(value = "获取模型图片")
    public void pngList(@PathVariable("id") String modelId, HttpServletResponse response) throws IOException {
        byte[] png = operatorModelService.getPng(modelId);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(png);
            outputStream.flush();
        }
    }

    @GetMapping("/editor")
    @ApiOperation(value = "跳转至编辑页面")
    public String editor() {
        return "modeler.html";
    }

    @GetMapping(value = "/getModelId")
    @ApiOperation(value = "生成modelId")
    public String getModelId(Model model) throws Exception {
        String modelId = operatorModelService.getModelId();
        model.addAttribute("modelId", modelId);
        return "addModel.html";
    }

    @ResponseBody
    @PostMapping(value = "/createModel")
    @ApiOperation(value = "创建模型")
    public String createModel(@RequestBody ModelVO modelVO) throws Exception {

        String name = modelVO.getName();
        String key = modelVO.getKey();
        String modelId = modelVO.getModelId();
        operatorModelService.createModel(name, key, modelId);
        return JsonUtil.genJsonSuccess("创建成功");
    }

    @ResponseBody
    @PostMapping("/publishModel")
    @ApiOperation(value = "发布模型")
    public String publishModel(String modelId) throws Exception {
        operatorModelService.publishModel(modelId);
        return JsonUtil.genJsonSuccess("publish model success");
    }

    @ResponseBody
    @PostMapping("/revokePublish")
    @ApiOperation(value = "撤回发布的model")
    public String revokePublish(String modelId) throws Exception {

        operatorModelService.revokePublish(modelId);
        return JsonUtil.genJsonSuccess("revoke model success");
    }

    @ResponseBody
    @RequestMapping("/deleteModel")
    @ApiOperation(value = "删除模型")
    public String deleteModel(String modelId) throws Exception {

        operatorModelService.deleteModel(modelId);
        return JsonUtil.genJsonSuccess("delete model success");
    }

    @ResponseBody
    @RequestMapping("/downloadModel")
    @ApiOperation(value = "下载模型")
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
