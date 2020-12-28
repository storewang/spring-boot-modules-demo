package com.wxy.spring.boot.app.event.sse;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 支付完成消息
 *
 * @author 石头
 * @Date 2020/12/28
 * @Version 1.0
 **/
@Data
public class PayCompletedEvent extends ApplicationEvent{
    private Long payRecordId;
    private PayEventType eventType;
    public PayCompletedEvent(Object source, Long payRecordId,PayEventType eventType) {
        super(source);
        this.payRecordId = payRecordId;
        this.eventType   = eventType;
    }
}
