package com.wxy.spring.boot.modules.api.log;

import com.wxy.spring.boot.modules.api.log.dto.LogDTO;

import java.util.List;

/**
 * 日志服务接口
 *
 * @author 石头
 * @Date 2020/12/24
 * @Version 1.0
 **/
public interface ILogService {
    List<LogDTO> getLogByUserId(Long userId);
}
