package com.wxy.spring.boot.app.surport;

import com.wxy.spring.boot.app.event.ModuleFinishedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 模块初始化完成处理
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class MouduleContextFinishedProcess implements ApplicationListener<ContextRefreshedEvent> {
    private String moduleName;
    private String basePackage;
    private ApplicationContext parent;
    public MouduleContextFinishedProcess(String moduleName, String basePackage,ApplicationContext parent){
        this.moduleName  = moduleName;
        this.basePackage = basePackage;
        this.parent      = parent;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(moduleName + "---------启动完成-----------");
        ModuleFinishedEvent finishedEvent = new ModuleFinishedEvent(moduleName,event.getApplicationContext());

        parent.publishEvent(finishedEvent);
    }

}
