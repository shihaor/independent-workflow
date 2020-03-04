package com.sdt.modeler.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sdt.modeler.service.ModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 原生Modeler接口
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@RestController
@RequestMapping("service")
@Api(value = "Modeler接口", tags = "Modeler接口")
public class ModelController {

    @Resource
    private ModelService modelService;

    @ApiOperation(value = "获取编辑完毕的资源")
    @RequestMapping(value = "/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {

        return modelService.getEditJson(modelId);
    }
    @ApiOperation(value = "保存编辑完毕的资源")
    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, String name, String description, String json_xml, String svg_xml) {
        modelService.saveModel(modelId, name, description, json_xml, svg_xml);
    }

    @ApiOperation(value = "获取编辑页面")
    @RequestMapping(value="/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getStencilet() {
        return modelService.getStencilet();
    }

}
