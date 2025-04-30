package com.ai.langchain.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String applicationName = event.getApplicationContext().getEnvironment().getProperty("spring.application.name","spring-boot-demo");
        log.info("-------------------------------------");
        log.info("------{} 启动完成------",applicationName);
        log.info("-------------------------------------");
    }
}
