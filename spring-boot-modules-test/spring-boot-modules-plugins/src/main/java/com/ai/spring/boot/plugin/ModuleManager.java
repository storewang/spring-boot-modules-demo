package com.ai.spring.boot.plugin;

import com.ai.spring.boot.plugin.annotations.ModuleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/20
 * @Version 1.0
 **/
@Slf4j
public class ModuleManager {
    /**
     * key   module-name
     * value ConfigurableApplicationContext
     * */
    private Map<String,ConfigurableApplicationContext> applicationContexts = new ConcurrentHashMap<>(16);
    /**
     * key   beanName
     * value module-name
     * */
    private Map<String,String> allBeanNames = new ConcurrentHashMap<>(16);
    /**
     * key   module-mainClass
     * value module-bean
     * */
    private Map<String,ModuleBean> moduleMainClass = new ConcurrentHashMap<>(16);
    /**
     * key   module-name
     * value module-mainClass
     * */
    private Map<String,String> moduleNames = new ConcurrentHashMap<>(16);
    /**
     * key   module-package-name
     * value module-name
     * */
    private Map<String,String> modulePackages = new ConcurrentHashMap<>(16);
    /***内部类实现单例模式******/
    private ModuleManager(){
    }
    private static class InstanceHolder{
        private static final ModuleManager INSTANCE = new ModuleManager();;
    }
    /**获取实例(单例)**/
    public static ModuleManager getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public ModuleBean getModuleBean(String moduleName){
        String mainClass = moduleNames.get(moduleName);
        if (!StringUtils.isEmpty(mainClass)){
            return moduleMainClass.get(mainClass);
        }
        return null;
    }
    public String getModuleMainClass(String moduleName){
        return moduleNames.get(moduleName);
    }
    public void addModuleMainClass(String beanClassName,ModuleBean moduleBean){
        moduleMainClass.put(beanClassName,moduleBean);
    }

    public void addModuleNames(String moduleName,String beanClassName){
        moduleNames.put(moduleName,beanClassName);
    }

    public void addModulePackages(String packageName,String moduleName){
        modulePackages.put(packageName,moduleName);
    }
    public Map<String,String> getModulePackages(){
        return modulePackages;
    }

    public void addBeanName(String beanName,String moduleName){
        allBeanNames.put(beanName,moduleName);
    }

    public void addApplicationContext(String moduleName,ConfigurableApplicationContext applicationContext){
        applicationContexts.put(moduleName,applicationContext);
    }

    public ConfigurableApplicationContext getApplicationContext(String moduleName){
        return applicationContexts.get(moduleName);
    }
    public <T> T getBean(String beanName,Class<T> rtnType){
        String moduleName = allBeanNames.get(beanName);
        if (StringUtils.isEmpty(moduleName)){
            log.warn("-------can't find [{}] bean in all module ------------------",beanName);
            return null;
        }
        ConfigurableApplicationContext applicationContext = applicationContexts.get(moduleName);
        if (applicationContext == null){
            log.warn("-------can't find [{}] module applicationContext in all modules------------------",moduleName);
            return null;
        }
        return applicationContext.getBean(beanName,rtnType);
    }
    public Object getBean(String beanName){
        String moduleName = allBeanNames.get(beanName);
        if (StringUtils.isEmpty(moduleName)){
            log.warn("-------can't find [{}] bean in all module ------------------",beanName);
            return null;
        }
        ConfigurableApplicationContext applicationContext = applicationContexts.get(moduleName);
        if (applicationContext == null){
            log.warn("-------can't find [{}] module applicationContext in all modules------------------",moduleName);
            return null;
        }
        return applicationContext.getBean(beanName);
    }
}
