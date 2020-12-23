package com.wxy.spring.boot.modules.demo.user.dao;

import com.wxy.spring.boot.modules.api.user.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * 用户操作类
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@Component
public class UserDao {
    public UserDTO findById(Long userId){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userId);
        userDTO.setUserName("用户1");
        return userDTO;
    }
    public UserDTO findByName(String userName){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(100L);
        userDTO.setUserName(userName);
        return userDTO;
    }
}
