package com.ai.spring.boot.plugin.bean;

import com.ai.spring.boot.plugin.ModuleManager;
import com.ai.spring.boot.plugin.annotations.ModuleBean;
import com.ai.spring.boot.plugin.annotations.Reference;
import com.ai.spring.boot.plugin.annotations.ReferenceField;
import com.ai.spring.boot.plugin.annotations.ReferenceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/8/21
 * @Version 1.0
 **/
@Slf4j
public class BeanInstantiatedProcessor implements SmartInitializingSingleton {
    private static final String SPLIT = "$$";
    private ConfigurableApplicationContext applicationContext;
    private String basePackage;
    public BeanInstantiatedProcessor(ConfigurableApplicationContext applicationContext,String basePackage){
        this.applicationContext = applicationContext;
        this.basePackage = basePackage;
    }
    @Override
    public void afterSingletonsInstantiated() {
        log.info("==============afterSingletonsInstantiated=================");
        /***启动子模块**/
        log.info("==============启动子模块=================");
        startModules();
        /**设置bean对应的module*/
        log.info("==============设置bean对应的module=================");
        initAllBean2Modules();
        log.info("==============刷新@Reference引用依赖=================");
        refleshReferenceField();
    }
    private void initAllBean2Modules(){
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        Arrays.stream(beanNames).forEach(beanName -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (!StringUtils.isEmpty(beanClassName) && beanClassName.indexOf(basePackage)>-1){
                //获取原始的className
                if (beanClassName.indexOf(SPLIT)>-1){
                    beanClassName = beanClassName.substring(0,beanClassName.indexOf(SPLIT));
                }
                String packageName = beanClassName.substring(0,beanClassName.lastIndexOf("."));

                String moduleName  = ModuleManager.getInstance().getModulePackages().get(packageName);
                if (!StringUtils.isEmpty(moduleName)){
                    ModuleManager.getInstance().addBeanName(beanName,moduleName);
                }
            }
        });
    }
    private void startModules(){
        Map<String,String> modulePackages = ModuleManager.getInstance().getModulePackages();
        modulePackages.entrySet().stream().forEach(entry -> {
            String moduleName      = entry.getValue();
            String moduleMainClass = ModuleManager.getInstance().getModuleMainClass(moduleName);
            ModuleBean moduleBean  = ModuleManager.getInstance().getModuleBean(moduleName);
            try {
                ConfigurableApplicationContext context = applicationContext;
                if (!StringUtils.isEmpty(moduleBean.parent())){
                    context = startModules(moduleMainClass,moduleBean);
                }
                startModules(moduleMainClass,moduleBean,context);
            }catch (Exception e){
                log.error(String.format("module %s started error:",moduleBean.value()),e);
            }
        });
    }
    private ConfigurableApplicationContext startModules(String mainClass,ModuleBean moduleBean,ConfigurableApplicationContext parentContext)throws ClassNotFoundException{
        String moduleName = getModuleName(moduleBean,mainClass);
        ConfigurableApplicationContext applicationContext = ModuleManager.getInstance().getApplicationContext(moduleName);
        if (applicationContext==null){
            Class<?> cls = Class.forName(mainClass);
            applicationContext = new SpringApplicationBuilder(cls).parent(parentContext).web(WebApplicationType.NONE).run();
            ModuleManager.getInstance().addApplicationContext(moduleName,applicationContext);
            return applicationContext;
        }
        return applicationContext;
    }
    private ConfigurableApplicationContext startModules(String mainClass,ModuleBean moduleBean)throws ClassNotFoundException{
        String parentModuleName = moduleBean.parent();
        ConfigurableApplicationContext applicationContext = this.applicationContext;
        if (!StringUtils.isEmpty(parentModuleName)){
            applicationContext = ModuleManager.getInstance().getApplicationContext(parentModuleName);
            // 不为空，则说明已经启动了，直接返回
            if (applicationContext!=null){
                return applicationContext;
            }
        }
        if (applicationContext==null){
            String parentMainClass  = ModuleManager.getInstance().getModuleMainClass(parentModuleName);
            ModuleBean parentModule = ModuleManager.getInstance().getModuleBean(parentModuleName);

            return startModules(parentMainClass,parentModule);
        }else{
            return startModules(mainClass,moduleBean,applicationContext);
        }
    }
    private void refleshReferenceField(){
        String[] beaNames = applicationContext.getBeanDefinitionNames();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        Arrays.stream(beaNames).forEach(beaName ->{
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beaName);
            String beanClassName = beanDefinition.getBeanClassName();
            Object bean = applicationContext.getBean(beaName);
            if (!StringUtils.isEmpty(beanClassName) && beanClassName.indexOf(basePackage)>-1 && bean!=null){
                List<ReferenceField> references = getReference(beanClassName,bean);
                popularBean(references,beaName);
            }
        });
    }
    private void popularBean(List<ReferenceField> references,String beaName){
        references.stream().forEach(referenceField -> {
            Field field    = referenceField.getField();
            String fieldName = field.getName();
            try{
                Object beanObj = referenceField.getBean();

                Reference reference = referenceField.getReference();
                String referenceVal = reference.value();
                ReferenceType referenceType = reference.type();
                if (StringUtils.isEmpty(referenceVal)){
                    referenceVal = field.getName();
                }
                if (ReferenceType.JVM == referenceType){
                    Object fieldVal =  ModuleManager.getInstance().getBean(referenceVal);
                    if (fieldVal!=null){
                        field.set(beanObj,fieldVal);
                    }
                }
            }catch (Exception e){
                log.warn("-------------popularBean.error:{}.{}---------------",beaName,fieldName);
            }
        });
    }

    private List<ReferenceField> getReference(String className,Object bean){
        try {
            if (className.indexOf("$$")>-1){
                className = className.substring(0,className.indexOf("$$"));
            }
            Class<?> cls = Class.forName(className);
            Field[] fields = cls.getDeclaredFields();

            List<ReferenceField> referenceFields = new ArrayList<>();
            for (Field field:fields){
                field.setAccessible(true);

                Reference reference = field.getAnnotation(Reference.class);
                if (reference!=null){
                    referenceFields.add(new ReferenceField(reference,field,bean));
                }
            }

            return referenceFields;
        } catch (ClassNotFoundException e) {
            log.warn("-------------getReference.error:{}---------------",className);
        }
        return Collections.EMPTY_LIST;
    }
    private String getModuleName(ModuleBean moduleBean,String mainClass){
        if (StringUtils.isEmpty(moduleBean.value())){
            return mainClass;
        }
        return moduleBean.value();
    }
}
