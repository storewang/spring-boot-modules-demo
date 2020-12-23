package com.wxy.spring.boot.modules.api.sys.dto;

/**
 * 角色信息
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
public class RoleDTO {
    private Long roleId;
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
