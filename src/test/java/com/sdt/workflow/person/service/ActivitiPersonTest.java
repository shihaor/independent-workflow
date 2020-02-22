package com.sdt.workflow.person.service;

import com.sdt.workflow.person.vo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试人员的添加和更新
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ActivitiPersonTest {

    @Resource
    private ActivitiPersonService activitiPersonService;

    /**
     * 添加人员
     */
    @Test
    public void addPerson() {

        Person person = new Person();
        person.setId("1");
        person.setName("史皓燃");
        person.setLastName("shr");
        activitiPersonService.addOrUpdate(person);
    }

    /**
     * 更新人员
     */
    @Test
    public void updatePerson() {

        Person person = new Person();
        person.setId("1");
        person.setName("shr");
        person.setLastName("史皓燃");
        activitiPersonService.addOrUpdate(person);
    }

    /**
     * 给人员添加机构
     */
    @Test
    public void addOrg() {

        Person person = new Person();
        person.setId("1");
        person.setName("史皓燃");
        person.setLastName("shr");
        person.setOrgId("1");
        activitiPersonService.addOrUpdate(person);
    }
}
