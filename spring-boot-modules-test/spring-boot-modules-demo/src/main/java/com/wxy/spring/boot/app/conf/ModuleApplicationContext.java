package com.wxy.spring.boot.app.conf;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模块Application 上下文
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public final class ModuleApplicationContext {
    private final static Map<String,Object> ALLDEPENDENCYSBEANS    = new ConcurrentHashMap<>(16);
    private final static Map<String,String> BEANSMODULE            = new ConcurrentHashMap<>(16);

    private final static ArrayBlockingQueue<LazyDependency> NEEDSLASYDEPENDENCYS       = new ArrayBlockingQueue<>(1000);
    private final static Map<String,ConfigurableListableBeanFactory> MODULEAPPLICATION = new ConcurrentHashMap<>(16);

    public static void addModuleApplication(String moduleName,ConfigurableListableBeanFactory context){
        MODULEAPPLICATION.putIfAbsent(moduleName,context);
    }

    public static ConfigurableListableBeanFactory getModuleApplication(String moduleName){
        return MODULEAPPLICATION.get(moduleName);
    }
    public static void addBeans(String typeName,Object bean){
        ALLDEPENDENCYSBEANS.putIfAbsent(typeName,bean);
    }

    public static Object getBean(String typeName){
        return ALLDEPENDENCYSBEANS.get(typeName);
    }
    public static void addBeansModule(String typeName,String moduleName){
        BEANSMODULE.putIfAbsent(typeName,moduleName);
    }

    public static String getBeansModuleName(String typeName){
        return BEANSMODULE.get(typeName);
    }
    public static boolean addLasyDependency(LazyDependency lazyDependency){
        return NEEDSLASYDEPENDENCYS.offer(lazyDependency);
    }

    public static void resolveLazyDependencys(){
        if (!NEEDSLASYDEPENDENCYS.isEmpty()){
            System.out.println("--------处理 need lazy dependency-----------");
            NEEDSLASYDEPENDENCYS.stream().forEach(lazyDependency -> resolveDependencyValue(lazyDependency));
        }
    }

    public static void resolveDependencyValue(LazyDependency dependency){
        try{
            String moduleName = dependency.getModuleName();
            String beanName   = dependency.getBeanName();
            String fieldName  = dependency.getFieldName();
            String dpcyName   = dependency.getDependencyName();

            ConfigurableListableBeanFactory beanFactory = MODULEAPPLICATION.get(moduleName);
            Object bean = beanFactory.getBean(beanName);
            Object fieldVal = ALLDEPENDENCYSBEANS.get(dpcyName);

            Field field = ReflectionUtils.findField(bean.getClass(), fieldName);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field,bean,fieldVal);
        }catch (Exception e){
            System.err.println(e);
        }
    }

    public static class LazyDependency{
        private String moduleName;
        private String beanName;
        private String fieldName;
        private String dependencyName;

        public String getDependencyName() {
            return dependencyName;
        }

        public void setDependencyName(String dependencyName) {
            this.dependencyName = dependencyName;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }
    }
}
