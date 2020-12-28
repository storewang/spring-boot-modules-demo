package com.wxy.spring.boot.modules.demo.log;

import com.wxy.spring.boot.modules.api.log.ILogService;
import com.wxy.spring.boot.modules.api.log.dto.LogDTO;
import com.wxy.spring.boot.modules.demo.log.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 日志服务
 *
 * @author 石头
 * @Date 2020/12/24
 * @Version 1.0
 **/
@Service
public class LogService implements ILogService {
    @Autowired
    private LogDao logDao;
    public LogService(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
    }
    @Override
    public List<LogDTO> getLogByUserId(Long userId) {
        return logDao.getLogByUserId(userId);
    }
}
