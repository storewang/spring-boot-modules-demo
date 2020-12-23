package com.wxy.spring.boot.modules.api.bus.dto;

import java.util.List;

/**
 * 业务实体
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
public class BusinessDTO {
    private Long userId;
    private String userName;
    private List<String> roleNames;

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

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
