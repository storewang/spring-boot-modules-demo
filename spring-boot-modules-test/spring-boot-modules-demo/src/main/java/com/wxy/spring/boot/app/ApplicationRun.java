package com.wxy.spring.boot.app;

import com.wxy.spring.boot.app.conf.ApplicationConf;
import com.wxy.spring.boot.app.event.ModuleFinishedListener;
import com.wxy.spring.boot.app.surport.MouduleBeanfactoryProcess;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 主程序
 * entityScan需要放在这里进行配置，要不能会出现子模块中找不到mapper bean的错误
 * eg: Caused by: java.lang.IllegalArgumentException: Not a managed type: class com.wxy.spring.boot.modules.demo.user.entity.UserEntity
 * 目前还不知道怎么解决
 * @author 石头
 * @Date 2020/12/21
 * @Version 1.0
 **/
@SpringBootApplication
@EntityScan(basePackages = {"com.wxy.spring.boot.modules.demo.user.entity"})
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
