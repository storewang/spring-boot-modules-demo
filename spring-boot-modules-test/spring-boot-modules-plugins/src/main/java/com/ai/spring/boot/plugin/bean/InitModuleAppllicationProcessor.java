package com.ai.spring.boot.plugin.bean;

import com.ai.spring.boot.plugin.ModuleManager;
import com.ai.spring.boot.plugin.annotations.ModuleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/22
 * @Version 1.0
 **/
@Slf4j
public class InitModuleAppllicationProcessor implements BeanFactoryPostProcessor {
    private static final String SPLIT = "$$";
    private ConfigurableApplicationContext applicationContext;
    private String basePackage;
    public InitModuleAppllicationProcessor(ConfigurableApplicationContext applicationContext,String basePackage){
        this.applicationContext = applicationContext;
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(beanName -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (!StringUtils.isEmpty(beanClassName) && beanClassName.indexOf(basePackage)>-1){
                //获取原始的className
                if (beanClassName.indexOf(SPLIT)>-1){
                    beanClassName = beanClassName.substring(0,beanClassName.indexOf(SPLIT));
                }
                String packageName = beanClassName.substring(0,beanClassName.lastIndexOf("."));
                ModuleBean moduleBean = getModuleBean(beanClassName);
                if (moduleBean!=null){
                    String moduleName = getModuleName(moduleBean,beanClassName);
                    ModuleManager.getInstance().addModuleMainClass(beanClassName,moduleBean);
                    ModuleManager.getInstance().addModuleNames(moduleName,beanClassName);

                    ModuleManager.getInstance().addModulePackages(packageName,moduleName);
                }
            }
        });
    }

    private ModuleBean getModuleBean(String className){
        try {
            Class<?> cls = Class.forName(className);
            ModuleBean moduleBean = cls.getAnnotation(ModuleBean.class);
            return moduleBean;
        } catch (ClassNotFoundException e) {
            log.warn("-------------getModuleBean.error---------------",e);
        }
        return null;
    }
    private String getModuleName(ModuleBean moduleBean,String mainClass){
        if (StringUtils.isEmpty(moduleBean.value())){
            return mainClass;
        }
        return moduleBean.value();
    }
}
