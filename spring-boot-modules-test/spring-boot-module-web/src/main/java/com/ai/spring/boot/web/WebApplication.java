package com.ai.spring.boot.web;

import com.ai.spring.boot.plugin.bean.BeanInstantiatedProcessor;
import com.ai.spring.boot.plugin.bean.InitModuleAppllicationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.ai.spring.boot"})
@Slf4j
public class WebApplication {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e) -> log.error(String.format("UncaughtException in Thread(%s):%s",t.getName(),e.getMessage()),e));
        ConfigurableApplicationContext webContext =  new SpringApplicationBuilder(WebApplication.class).run(args);
//        ConfigurableApplicationContext daoContext =  new SpringApplicationBuilder(DaoApplication.class).web(WebApplicationType.NONE).run(args);
//        ConfigurableApplicationContext svcContext =  new SpringApplicationBuilder(SvcApplication.class).parent(daoContext).web(WebApplicationType.NONE).run(args);

        new BeanInstantiatedProcessor(webContext,"com.ai.spring.boot").afterSingletonsInstantiated();
    }

    @Bean
    public InitModuleAppllicationProcessor moduleAppllicationProcessor(ConfigurableApplicationContext applicationContext){
        return new InitModuleAppllicationProcessor(applicationContext,"com.ai.spring.boot");
    }
}
