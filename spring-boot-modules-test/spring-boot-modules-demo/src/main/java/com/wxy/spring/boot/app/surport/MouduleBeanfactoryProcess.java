package com.wxy.spring.boot.app.surport;

import com.wxy.spring.boot.app.conf.ModuleApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * bean factory process
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class MouduleBeanfactoryProcess implements BeanFactoryPostProcessor{
    private static final String BEAN = EntityScanPackages.class.getName();

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
//                System.out.println("--------beanClassName:------------"+beanClassName);
            }
        });

        ModuleApplicationContext.addModuleApplication(moduleName,beanFactory);
//        System.out.println(moduleName + "---------BeanFactoryPostProcessor.End-----------");

    }
}
