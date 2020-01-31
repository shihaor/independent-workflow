package com.sdt.workflow.service;

import com.sdt.workflow.dao.UserDao;
import com.sdt.workflow.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试用户服务，主要用来测试jpa是否集成成功
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class UserServiceTest {

    @Resource
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setOrgId(1);
        user.setUserName("史皓燃");
        userDao.save(user);
    }
}
