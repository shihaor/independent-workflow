package com.sdt.workflow.dao;

import com.sdt.workflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User信息的查询接口
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
