package com.wxy.spring.boot.app.surport;

import com.wxy.spring.boot.app.conf.ModuleApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * Module Bean Factory
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class ModuleListtableBeanFactory extends DefaultListableBeanFactory {
    private String moduleName;
    private String basePackage;
    public ModuleListtableBeanFactory(String moduleName,String basePackage){
        this.moduleName  = moduleName;
        this.basePackage = basePackage;
    }
    @Nullable
    @Override
    public Object doResolveDependency(DependencyDescriptor descriptor, @Nullable String beanName,
                                      @Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException {
        String beanClassName = this.getBeanDefinition(beanName).getBeanClassName();
        Class<?> type = descriptor.getDependencyType();
        if (beanClassName!=null && beanClassName.indexOf(basePackage)> -1){
            Map<String, Object> matchingBeans = findAutowireCandidates(beanName, type, descriptor);
            boolean required = getAutowireCandidateResolver().isRequired(descriptor);
            if (matchingBeans.isEmpty() && required) {
                Object bean = ModuleApplicationContext.getBean(type.getName());
                if (bean == null){
                    ModuleApplicationContext.LazyDependency lazyDependency = new ModuleApplicationContext.LazyDependency();
                    lazyDependency.setModuleName(moduleName);
                    lazyDependency.setBeanName(beanName);
                    lazyDependency.setFieldName(descriptor.getDependencyName());
                    lazyDependency.setDependencyName(type.getName());

                    System.out.println(moduleName+", beanName="+beanName + ",fieldName="+descriptor.getDependencyName());
                    ModuleApplicationContext.addLasyDependency(lazyDependency);
                }
                return bean;
            }
        }

        return super.doResolveDependency(descriptor,beanName,autowiredBeanNames,typeConverter);
    }
}
