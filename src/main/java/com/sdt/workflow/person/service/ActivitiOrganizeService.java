package com.sdt.workflow.person.service;

import com.sdt.workflow.person.vo.Organize;

/**
 * 工作流机构管理服务接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public interface ActivitiOrganizeService {

    /**
     * 添加机构
     *
     * @param organize 待添加的机构信息
     */
    void addOrUpdate(Organize organize);
}
