package com.wxy.spring.boot.app;

import com.wxy.spring.boot.app.conf.ApplicationConf;
import com.wxy.spring.boot.app.event.ModuleFinishedListener;
import com.wxy.spring.boot.app.surport.MouduleBeanfactoryProcess;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 主程序
 *
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@SpringBootApplication
public class ApplicationRun implements ApplicationListener<ApplicationReadyEvent>{
    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationRun.class).run(args);
    }

    @Bean
    public MouduleBeanfactoryProcess beanfactoryProcess(){
        MouduleBeanfactoryProcess mainApplicationProcess = new MouduleBeanfactoryProcess("main-application","com.wxy.spring.boot.app");
        return mainApplicationProcess;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("-----------main-application启动完成-------------");
        ApplicationConf applicationConf = new ApplicationConf();
        applicationConf.init(event.getApplicationContext());
    }

    @Bean
    public ModuleFinishedListener moduleFinishedListener(){
        ModuleFinishedListener listener = new ModuleFinishedListener(new ArrayList(Arrays.asList("user-service","role-service","bus-service")));
        return listener;
    }
}
