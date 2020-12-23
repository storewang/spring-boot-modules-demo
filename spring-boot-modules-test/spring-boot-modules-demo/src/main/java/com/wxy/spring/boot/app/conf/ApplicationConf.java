package com.wxy.spring.boot.app.conf;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 子应用配置类
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class ApplicationConf {

    public void init(ApplicationContext parent){
        System.out.println("--------ApplicationConf-------");
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();
        poolExecutor.setCorePoolSize(1);
        poolExecutor.setQueueCapacity(3);
        poolExecutor.setMaxPoolSize(3);
        poolExecutor.setKeepAliveSeconds(8);
        poolExecutor.setThreadNamePrefix("sub-modules");
        poolExecutor.setAllowCoreThreadTimeOut(true);
        poolExecutor.initialize();

        poolExecutor.execute(new ModuleApplicationStarter("user-service","com.wxy.spring.boot.modules.demo.user",parent));
        poolExecutor.execute(new ModuleApplicationStarter("role-service","com.wxy.spring.boot.modules.demo.sys",parent));
        poolExecutor.execute(new ModuleApplicationStarter("bus-service","com.wxy.spring.boot.modules.demo.bus",parent));

    }

}
