package com.wxy.spring.boot.modules.demo.sys;

import com.wxy.spring.boot.modules.api.sys.IRoleService;
import com.wxy.spring.boot.modules.api.sys.dto.RoleDTO;
import com.wxy.spring.boot.modules.demo.sys.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@Service
public class RoleService implements IRoleService{
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<RoleDTO> findByUserId(Long userId) {
        return roleDao.findByUserId(userId);
    }
}
