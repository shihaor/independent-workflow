package com.sdt.workflow.entity;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

/**
 * 测试的用户表
 *
 * @author shihaoran
 * @date 2020/1/31
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 4728901180909900064L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name", length = 64)
    private String userName;

    @Column(name = "orgId", length = 20)
    private Integer orgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
