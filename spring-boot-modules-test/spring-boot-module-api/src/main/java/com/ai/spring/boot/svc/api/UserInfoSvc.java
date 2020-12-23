package com.ai.spring.boot.svc.api;

import com.ai.spring.boot.svc.vo.UserInfoDTO;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
public interface UserInfoSvc {
    UserInfoDTO findByUserName(String userName);
    Long save(UserInfoDTO userInfo);
}
