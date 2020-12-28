package com.wxy.spring.boot.app.event.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付完成消息监听器
 *
 * @author 石头
 * @Date 2020/12/28
 * @Version 1.0
 **/
@Component
@Slf4j
public class PayCompletedListener implements ApplicationListener<PayCompletedEvent>{
    private static Map<Long,SseEmitter> sseEmitters = new ConcurrentHashMap<>(16);

    public void addSseEmitters(Long payRecordId, SseEmitter sseEmitter) {
        sseEmitters.putIfAbsent(payRecordId, sseEmitter);
    }

    @Override
    public void onApplicationEvent(PayCompletedEvent event) {
        Long payRecordId       = event.getPayRecordId();
        PayEventType eventType = event.getEventType();
        try {
            log.info("收到消息[{}],将处理消息结果推送给客户端。",payRecordId);
            SseEmitter sseEmitter = sseEmitters.get(payRecordId);
            if (PayEventType.PAY_BACK == eventType && sseEmitter!=null){
                sseEmitter.send("支付成功");
            }else if (sseEmitter!=null){
                sseEmitters.remove(payRecordId);
                sseEmitter.complete();
            }
        } catch (IOException e) {
            //消息处理失败，记录失败日志，以便重新处理，或是交于人工处理
            log.info("消息[{}]处理失败，记录失败日志，以便重新处理，或是交于人工处理",payRecordId);
        }
    }
}
