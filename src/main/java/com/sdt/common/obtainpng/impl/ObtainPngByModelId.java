package com.sdt.common.obtainpng.impl;

import com.sdt.common.obtainpng.IObtainPng;
import org.activiti.engine.RepositoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通过流程定义id获取图片字节流
 *
 * @author shihaoran
 * @date 2020/3/12
 */
@Scope("prototype")
@Service(value = "ObtainPngByModelId")
public class ObtainPngByModelId implements IObtainPng {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public byte[] obtainPngByte(String condition) {
        return repositoryService.getModelEditorSourceExtra(condition);
    }
}
