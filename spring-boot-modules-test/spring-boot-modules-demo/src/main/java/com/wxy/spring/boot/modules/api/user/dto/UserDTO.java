package com.wxy.spring.boot.modules.api.user.dto;

/**
 * 用户实体
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
public class UserDTO {
    private Long userId;
    private String userName;

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
}
