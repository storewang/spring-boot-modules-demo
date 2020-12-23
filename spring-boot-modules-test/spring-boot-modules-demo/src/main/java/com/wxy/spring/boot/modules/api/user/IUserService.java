package com.wxy.spring.boot.modules.api.user;

import com.wxy.spring.boot.modules.api.user.dto.UserDTO;

/**
 * 用户服务接口
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
public interface IUserService {
    UserDTO findById(Long userId);
    UserDTO findByName(String userName);
}
