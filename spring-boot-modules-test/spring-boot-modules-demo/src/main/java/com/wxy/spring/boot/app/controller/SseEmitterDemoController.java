package com.wxy.spring.boot.app.controller;

import com.wxy.spring.boot.app.event.sse.PayCompletedEvent;
import com.wxy.spring.boot.app.event.sse.PayCompletedListener;
import com.wxy.spring.boot.app.event.sse.PayEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 使用sseEmitter（server-send-events） 实现支付回调后，主动推送到客户端
 *
 * @author 石头
 * @Date 2020/12/28
 * @Version 1.0
 **/
@RestController
public class SseEmitterDemoController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private PayCompletedListener payCompletedListener;

    @GetMapping("/pay-listener")
    public SseEmitter push(@RequestParam Long payRecordId){
        // 默认30秒超时,设置为0L则永不超时
        final SseEmitter emitter = new SseEmitter(0L);
        payCompletedListener.addSseEmitters(payRecordId,emitter);

        return emitter;
    }

    @GetMapping("/pay-callback")
    public ResponseEntity<String> payCallback(@RequestParam Long payRecordId){
        applicationContext.publishEvent(new PayCompletedEvent(this,payRecordId, PayEventType.PAY_BACK));
        return ResponseEntity.ok("请到监听处查看消息");
    }

    @GetMapping("/close-listener")
    public ResponseEntity<String> completeSseEmitter(@RequestParam Long payRecordId){
        applicationContext.publishEvent(new PayCompletedEvent(this,payRecordId,PayEventType.CLOSE_LISTENER));
        return ResponseEntity.ok(String.format("销毁[%s]对应的监听器",payRecordId));
    }
}
