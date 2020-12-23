package com.wxy.spring.boot.app.surport;

import com.wxy.spring.boot.app.conf.ModuleApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.beans.PropertyDescriptor;

/**
 * 模块中bean实例化处理
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class MouduleBeanPostProcess implements InstantiationAwareBeanPostProcessor,ApplicationContextAware {
    private String moduleName;
    private String basePackage;
    private ApplicationContext context;
    public MouduleBeanPostProcess(String moduleName, String basePackage){
        this.moduleName  = moduleName;
        this.basePackage = basePackage;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(moduleName + "-----------before init class :-----------"+beanClass);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(moduleName + "-----------after init bean :-----------"+beanName);
        return true;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(moduleName + "-----------post after init bean :-----------"+beanName);
        String name = bean.getClass().getName();
        if (name.indexOf(basePackage)>-1){
            ModuleApplicationContext.addBeans(name,bean);
            ModuleApplicationContext.addBeansModule(name,moduleName);
        }
        return bean;
    }
    @Override
    public PropertyValues postProcessPropertyValues(
            PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println(moduleName + "-----------set bean's propertys :-----------" + beanName);
        return pvs;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        this.context = applicationContext;
    }
}
