package com.wxy.spring.boot.app.conf;

import com.wxy.spring.boot.app.surport.ModuleListtableBeanFactory;
import com.wxy.spring.boot.app.surport.MouduleBeanPostProcess;
import com.wxy.spring.boot.app.surport.MouduleContextFinishedProcess;
import com.wxy.spring.boot.app.surport.MouduleBeanfactoryProcess;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 子模块启动
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class ModuleApplicationStarter implements Runnable{
    private String moduleName;
    private String basePackage;
    private ApplicationContext parent;
    public ModuleApplicationStarter(String moduleName,String basePackage,ApplicationContext parent){
        this.moduleName  = moduleName;
        this.basePackage = basePackage;
        this.parent      = parent;
    }

    @Override
    public void run() {
        System.out.println("--------start------------"+moduleName);
        ModuleListtableBeanFactory beanFactory = new ModuleListtableBeanFactory(moduleName,basePackage);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(beanFactory);
        applicationContext.setDisplayName(moduleName);
        applicationContext.scan(basePackage);
        applicationContext.setEnvironment((ConfigurableEnvironment)parent.getEnvironment());
        applicationContext.setParent(parent);

        applicationContext.registerBean(MouduleBeanfactoryProcess.class,moduleName,basePackage);
        applicationContext.registerBean(MouduleContextFinishedProcess.class,moduleName,basePackage,parent);
        applicationContext.registerBean(MouduleBeanPostProcess.class,moduleName,basePackage);

        applicationContext.refresh();
    }
}
