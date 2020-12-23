package com.ai.spring.boot.dao.impl;

import com.ai.spring.boot.dao.api.UserInfoDao;
import com.ai.spring.boot.dao.entity.UserInfoEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
@Component
public class UserInfoDaoImpl implements UserInfoDao {
    private Map<Long,UserInfoEntity> dataIds = new ConcurrentHashMap<>(16);
    private Map<String,UserInfoEntity> dataNames = new ConcurrentHashMap<>(16);
    @Override
    public UserInfoEntity save(UserInfoEntity userInfoEntity) {
        userInfoEntity.setId(100001L);
        dataIds.put(100001L,userInfoEntity);
        dataNames.put(userInfoEntity.getUserName(),userInfoEntity);
        return userInfoEntity;
    }

    @Override
    public UserInfoEntity findByUserName(String userName) {
        return dataNames.get(userName);
    }
}
