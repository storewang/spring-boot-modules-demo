package com.wxy.spring.boot.app.surport;

import com.wxy.spring.boot.app.conf.ModuleApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * bean factory process
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class MouduleBeanfactoryProcess implements BeanFactoryPostProcessor{
    private String moduleName;
    private String basePackage;
    public MouduleBeanfactoryProcess(String moduleName,String basePackage){
        this.moduleName  = moduleName;
        this.basePackage = basePackage;
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(moduleName + "---------BeanFactoryPostProcessor-----------");
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(beanName -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();

            if (beanClassName!=null && beanClassName.indexOf(basePackage)> -1){
                System.out.println("--------beanClassName:------------"+beanClassName);
            }
        });

        ModuleApplicationContext.addModuleApplication(moduleName,beanFactory);
        System.out.println(moduleName + "---------BeanFactoryPostProcessor.End-----------");
    }
}
