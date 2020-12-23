package com.ai.spring.boot.plugin.bean;

import com.ai.spring.boot.plugin.ModuleManager;
import com.ai.spring.boot.plugin.annotations.ModuleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/20
 * @Version 1.0
 **/
//@Component
@Slf4j
public class BeansFactoryProcessor implements BeanFactoryPostProcessor{
    private ConfigurableApplicationContext applicationContext;
    private String basePackage;
    public BeansFactoryProcessor(ConfigurableApplicationContext applicationContext,String basePackage){
        this.applicationContext = applicationContext;
        this.basePackage = basePackage;
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("-->>>-----BeansFactoryProcessor:{}---->>>--------",configurableListableBeanFactory.getBeanDefinitionCount());

        String[] beanNames = configurableListableBeanFactory.getBeanDefinitionNames();

        String moduleName = getModuleName(configurableListableBeanFactory);
        if (!StringUtils.isEmpty(moduleName)){
            ModuleManager.getInstance().addApplicationContext(moduleName,applicationContext);
        }

        Arrays.stream(beanNames).forEach(beanName -> {
            BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (!StringUtils.isEmpty(beanClassName) && beanClassName.indexOf(basePackage)>-1){
                ModuleManager.getInstance().addBeanName(beanName,moduleName);
            }
        });

        log.info("--<<<<<-----BeansFactoryProcessor：{}----<<<<--------",moduleName);

    }

    private String getModuleName(ConfigurableListableBeanFactory configurableListableBeanFactory){
        try {
            String[] beanNames = configurableListableBeanFactory.getBeanDefinitionNames();
            AtomicReference<String> moduleName = new AtomicReference<>();
            Arrays.stream(beanNames).forEach(beanName -> {
                BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(beanName);
                String beanClassName = beanDefinition.getBeanClassName();

                if (!StringUtils.isEmpty(beanClassName) && beanClassName.indexOf(basePackage)>-1){
                    String module = getModuleName(beanClassName);
                    if (!StringUtils.isEmpty(module)){
                        moduleName.getAndSet(getModuleName(beanClassName));
                    }
                }
            });
            return moduleName.get();
        } catch (Exception e) {
            log.error("获取模板名称失败:",e);
        }
        return null;
    }

    private String getModuleName(String className){
        try {
            if (className.indexOf("$$")>-1){
                className = className.substring(0,className.indexOf("$$"));
            }
            Class<?> cls = Class.forName(className);
            ModuleBean moduleBean = cls.getAnnotation(ModuleBean.class);
            if (moduleBean!=null){
                return StringUtils.isEmpty(moduleBean.value())?cls.getSimpleName():moduleBean.value();
            }
        } catch (ClassNotFoundException e) {
            log.warn("-------------getModuleName---------------",e);
        }
        return null;
    }
}
