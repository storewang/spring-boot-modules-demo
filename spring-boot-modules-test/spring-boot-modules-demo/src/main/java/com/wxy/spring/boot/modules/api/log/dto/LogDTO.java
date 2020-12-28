package com.wxy.spring.boot.modules.api.log.dto;

/**
 * 日志信息
 *
 * @author 石头
 * @Date 2020/12/24
 * @Version 1.0
 **/
public class LogDTO {
    private String msg;
    private Long logId;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }
}
