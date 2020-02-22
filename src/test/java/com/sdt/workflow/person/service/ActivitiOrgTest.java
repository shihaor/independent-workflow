package com.sdt.workflow.person.service;

import com.sdt.workflow.person.vo.Organize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 工作流机构测试
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ActivitiOrgTest {

    @Resource
    private ActivitiOrganizeService activitiOrganizeService;

    /**
     * 新增机构
     */
    @Test
    public void add() {

        Organize organize = new Organize();
        organize.setOrgId("1");
        organize.setName("比尔吉沃特");
        activitiOrganizeService.addOrUpdate(organize);
    }

    /**
     * 更新机构
     */
    @Test
    public void updateOrg() {

        Organize organize = new Organize();
        organize.setOrgId("1");
        organize.setName("艾欧尼亚");
        activitiOrganizeService.addOrUpdate(organize);
    }
}
