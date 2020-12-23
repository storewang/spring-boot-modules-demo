package com.wxy.spring.boot.modules.api.sys;

import com.wxy.spring.boot.modules.api.sys.dto.RoleDTO;

import java.util.List;

/**
 * 角色服务接口
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
public interface IRoleService {
    List<RoleDTO> findByUserId(Long userId);
}
