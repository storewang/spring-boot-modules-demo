package com.ai.spring.boot.plugin.bean;

import com.ai.spring.boot.plugin.ModuleManager;
import com.ai.spring.boot.plugin.annotations.ModuleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/20
 * @Version 1.0
 **/
//@Component
@Slf4j
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
//        log.info("--------BeanPostProcessor.postProcessBeforeInitialization:{}----------------------->",beanName);

        ModuleBean moduleBean = bean.getClass().getAnnotation(ModuleBean.class);
        if (moduleBean!=null){
            String moduleName = moduleBean.value();
            if (!StringUtils.isEmpty(moduleName)){
                ModuleManager.getInstance().addBeanName(beanName,moduleName);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)throws BeansException {
//        log.info("<--------BeanPostProcessor.postProcessAfterInitialization:{}-----------------------",beanName);
        return bean;
    }
}
