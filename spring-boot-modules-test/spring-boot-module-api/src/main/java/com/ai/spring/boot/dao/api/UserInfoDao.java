package com.ai.spring.boot.dao.api;

import com.ai.spring.boot.dao.entity.UserInfoEntity;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
public interface UserInfoDao {
    UserInfoEntity save(UserInfoEntity userInfoEntity);
    UserInfoEntity findByUserName(String userName);
}
