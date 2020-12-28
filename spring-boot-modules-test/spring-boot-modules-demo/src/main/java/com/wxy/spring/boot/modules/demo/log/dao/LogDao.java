package com.wxy.spring.boot.modules.demo.log.dao;

import com.wxy.spring.boot.modules.api.log.dto.LogDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 日志数据操作
 *
 * @author 石头
 * @Date 2020/12/24
 * @Version 1.0
 **/
@Component
public class LogDao {
    public List<LogDTO> getLogByUserId(Long userId){
        LogDTO logDTO = new LogDTO();
        logDTO.setLogId(1L);
        logDTO.setMsg("hello world !");

        LogDTO logDTO1 = new LogDTO();
        logDTO1.setLogId(2L);
        logDTO1.setMsg("测试日志 !");

        LogDTO logDTO2 = new LogDTO();
        logDTO2.setLogId(3L);
        logDTO2.setMsg("登录日志 !");

        return new ArrayList(Arrays.asList(logDTO,logDTO1,logDTO2));
    }
}
