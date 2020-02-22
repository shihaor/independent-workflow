package com.sdt.modeler.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sdt.modeler.service.ModelService;
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
public class ModelController {

    @Resource
    private ModelService modelService;

    @RequestMapping(value = "/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
    public ObjectNode getEditorJson(@PathVariable String modelId) {

        return modelService.getEditJson(modelId);
    }

    @RequestMapping(value = "/model/{modelId}/save", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, String name, String description, String json_xml, String svg_xml) {
        modelService.saveModel(modelId, name, description, json_xml, svg_xml);
    }

    @RequestMapping(value="/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getStencilet() {
        return modelService.getStencilet();
    }

}
