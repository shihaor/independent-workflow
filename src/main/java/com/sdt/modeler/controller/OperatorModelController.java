package com.sdt.modeler.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.bean.PagerBean;
import com.sdt.common.exception.GlobalException;
import com.sdt.common.obtainpng.IObtainPng;
import com.sdt.common.result.CodeMsg;
import com.sdt.common.result.Result;
import com.sdt.common.utils.CommonUtils;
import com.sdt.modeler.service.OperatorModelService;
import com.sdt.modeler.vo.ModelVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@Controller
@Api(value = "操作模型的接口", tags = "操作模型的接口")
public class OperatorModelController {

    @Resource
    private OperatorModelService operatorModelService;

    @Resource
    @Qualifier("ObtainPngByModelId")
    private IObtainPng ObtainPngByModelId;

    /**
     * 不加produces前端layui解析JSON乱码
     *
     * @return 模型数据
     * @throws JsonProcessingException json转化错误
     */
    @ApiOperation(value = "列出全部的model")
    @PostMapping(value = "/listModel")
    public Result<Object> listModel(PagerBean pagerBean) {
        List<org.activiti.engine.repository.Model> resultList = operatorModelService.findAllModel();
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @GetMapping("/modelIdList")
    @ApiOperation(value = "列出全部的modelId")
    public Result<Object> modelIdList() {
        List<ModelVO> modelIdList = operatorModelService.findAllModelIdList();
        return Result.success(modelIdList);
    }

    @GetMapping("/getPng/{id}")
    @ApiOperation(value = "获取模型图片")
    public void pngList(@PathVariable("id") String modelId, HttpServletResponse response) throws IOException {
        byte[] png = ObtainPngByModelId.obtainPngByte(modelId);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(png);
            outputStream.flush();
        }
    }

    @GetMapping(value = "/getModelId")
    @ApiOperation(value = "生成modelId")
    public Result<Object> getModelId() {
        String modelId = operatorModelService.getModelId();
        return Result.success(modelId);
    }

    @PostMapping(value = "/createModel")
    @ApiOperation(value = "创建模型")
    public Result<Object> createModel(ModelVO modelVO) {

        String name = modelVO.getName();
        String key = modelVO.getKey();
        String modelId = modelVO.getModelId();
        operatorModelService.createModel(name, key, modelId);
        return Result.success("创建成功");
    }

    @PostMapping("/publishModel")
    @ApiOperation(value = "发布模型")
    public Result<Object> publishModel(String modelId) throws IOException {
        operatorModelService.publishModel(modelId);
        return Result.success("发布模型成功");
    }

    @PostMapping("/revokePublish")
    @ApiOperation(value = "撤回发布的model")
    public Result<Object> revokePublish(String modelId) {

        operatorModelService.revokePublish(modelId);
        return Result.success("撤销模型成功");
    }

    @PostMapping("/deleteModel")
    @ApiOperation(value = "删除模型")
    public Result<Object> deleteModel(String modelId) {

        operatorModelService.deleteModel(modelId);
        return Result.success("删除模型成功");
    }

    @GetMapping("/downloadModel")
    @ApiOperation(value = "下载模型")
    public void downloadModel(HttpServletResponse response, String modelId) throws IOException {
        byte[] bytes;
        try {
            bytes = operatorModelService.downloadModel(modelId, response);
        } catch (Exception e) {
            response.sendError(506, CodeMsg.FILE_DOWNLOAD_FAIL.getMsg());
            throw new GlobalException(CodeMsg.FILE_DOWNLOAD_FAIL);
        }
        String filename = new Date().toString() + ".bpmn20.xml";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=\""
                + new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"");
        try(PrintWriter outputStream = response.getWriter()) {
            outputStream.print(new String(bytes, StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}
