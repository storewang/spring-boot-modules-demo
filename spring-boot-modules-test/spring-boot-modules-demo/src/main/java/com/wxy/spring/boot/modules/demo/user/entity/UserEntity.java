package com.wxy.spring.boot.modules.demo.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户entity
 *
 * @author 石头
 * @Date 2020/12/25
 * @Version 1.0
 **/
@Entity
@Table(name = "sys_user")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "phone")
    private String phone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
