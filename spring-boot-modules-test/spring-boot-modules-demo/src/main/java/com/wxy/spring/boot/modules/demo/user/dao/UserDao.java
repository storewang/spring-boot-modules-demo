package com.wxy.spring.boot.modules.demo.user.dao;

import com.wxy.spring.boot.modules.api.user.dto.UserDTO;
import com.wxy.spring.boot.modules.demo.user.entity.UserEntity;
import com.wxy.spring.boot.modules.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 用户操作类
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@Component
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    public UserDTO findById(Long userId){
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        UserDTO result = userEntity.map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUserName(user.getUserName());
            return userDTO;
        }).orElseGet(() -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userId);
            userDTO.setUserName("用户1");
            return userDTO;
        });


        return result;
    }
    public UserDTO findByName(String userName){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(100L);
        userDTO.setUserName(userName);
        return userDTO;
    }
}
