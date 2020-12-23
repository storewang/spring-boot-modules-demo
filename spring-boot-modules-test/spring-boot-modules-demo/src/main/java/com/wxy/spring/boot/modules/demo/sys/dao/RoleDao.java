package com.wxy.spring.boot.modules.demo.sys.dao;

import com.wxy.spring.boot.modules.api.sys.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 角色操作类
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@Component
public class RoleDao {

    public List<RoleDTO> findByUserId(Long userId){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(1L);
        roleDTO.setRoleName("管理员");

        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setRoleId(1L);
        roleDTO1.setRoleName("开发人员");

        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO2.setRoleId(1L);
        roleDTO2.setRoleName("技术经理");

        if (userId!=null && userId.equals(Long.valueOf(1))) {
            return Arrays.asList(roleDTO,roleDTO1,roleDTO2);
        }

        return Arrays.asList(roleDTO1,roleDTO2);
    }
}
