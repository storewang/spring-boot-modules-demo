package com.wxy.spring.boot.modules.demo.bus;

import com.wxy.spring.boot.modules.api.bus.IBusniseService;
import com.wxy.spring.boot.modules.api.bus.dto.BusinessDTO;
import com.wxy.spring.boot.modules.api.sys.dto.RoleDTO;
import com.wxy.spring.boot.modules.api.user.dto.UserDTO;
import com.wxy.spring.boot.modules.demo.sys.RoleService;
import com.wxy.spring.boot.modules.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务服务类
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
@Service
class BusniseService implements IBusniseService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public BusinessDTO findByUserId(Long userId) {
        UserDTO userDTO = userService.findById(userId);
        List<RoleDTO> roleDTOS = roleService.findByUserId(userId);

        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setUserId(userId);
        businessDTO.setUserName(userDTO.getUserName());
        businessDTO.setRoleNames(roleDTOS.stream().map(RoleDTO::getRoleName).collect(Collectors.toList()));

        return businessDTO;
    }

    @Override
    public BusinessDTO findByUserName(String userName) {
        UserDTO userDTO = userService.findByName(userName);
        List<RoleDTO> roleDTOS = roleService.findByUserId(userDTO.getUserId());

        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setUserId(userDTO.getUserId());
        businessDTO.setUserName(userDTO.getUserName());
        businessDTO.setRoleNames(roleDTOS.stream().map(RoleDTO::getRoleName).collect(Collectors.toList()));

        return businessDTO;
    }

}
