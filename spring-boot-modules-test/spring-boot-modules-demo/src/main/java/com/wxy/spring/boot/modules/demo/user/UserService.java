package com.wxy.spring.boot.modules.demo.user;

import com.wxy.spring.boot.modules.api.user.IUserService;
import com.wxy.spring.boot.modules.api.user.dto.UserDTO;
import com.wxy.spring.boot.modules.demo.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@Service
public class UserService implements IUserService{
    @Autowired
    private UserDao userDao;
    @Override
    public UserDTO findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public UserDTO findByName(String userName) {
        return userDao.findByName(userName);
    }
}
