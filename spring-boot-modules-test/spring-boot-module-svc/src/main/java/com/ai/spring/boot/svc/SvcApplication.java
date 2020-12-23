package com.ai.spring.boot.svc;

import com.ai.spring.boot.plugin.annotations.ModuleBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/19
 * @Version 1.0
 **/
@SpringBootApplication
@ModuleBean(value = "spring-boot-module-svc",parent = "spring-boot-module-dao")
public class SvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SvcApplication.class,args);
    }

//    @Bean
//    public BeanPostProcessor beanPostProcessor(){
//        return new BeanPostProcessor();
//    }
//    @Bean
//    public BeansFactoryProcessor beansFactoryProcessor(ConfigurableApplicationContext applicationContext){
//        return new BeansFactoryProcessor(applicationContext,"com.ai.spring.boot.svc");
//    }
}
