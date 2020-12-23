package com.ai.spring.boot.svc.impl;

import com.ai.spring.boot.dao.api.UserInfoDao;
import com.ai.spring.boot.dao.entity.UserInfoEntity;
import com.ai.spring.boot.svc.api.UserInfoSvc;
import com.ai.spring.boot.svc.vo.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
@Service("userInfoSvcImpl")
@Slf4j
public class UserInfoSvcImpl implements UserInfoSvc {
    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfoSvcImpl(){
        log.info("------UserInfoSvcImpl.construct------------");
    }
    @PostConstruct
    public void init(){
        log.info("------UserInfoSvcImpl.init------------");
    }
    @Override
    public UserInfoDTO findByUserName(String userName) {
        UserInfoEntity userInfoEntity = userInfoDao.findByUserName(userName);
        if (userInfoEntity == null){
            return new UserInfoDTO();
        }
        return new UserInfoDTO(userInfoEntity.getId(),userInfoEntity.getUserName(),userInfoEntity.getAddress(),userInfoEntity.getAge());
    }

    @Override
    public Long save(UserInfoDTO userInfo) {
        UserInfoEntity userInfoEntity = userInfoDao.save(new UserInfoEntity(userInfo.getId(),userInfo.getUserName(),userInfo.getAddress(),userInfo.getAge()));
        return userInfoEntity.getId();
    }

    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
}
