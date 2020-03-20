package com.sdt.workflow.core;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * 主键生成
 *
 * @author shihaoran
 * @date 2019/11/26
 */
public class IdGenerators implements IdGenerator {

    @Override
    public String getNextId() {
        return UUID.randomUUID().toString();
    }
}