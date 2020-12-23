package com.wxy.spring.boot.modules.api.bus;

import com.wxy.spring.boot.modules.api.bus.dto.BusinessDTO;

/**
 * 业务服务接口
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
public interface IBusniseService {
    BusinessDTO findByUserId(Long userId);
    BusinessDTO findByUserName(String userName);
}
