package com.wxy.spring.boot.app.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * 子模块启动完成事件
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/

public class ModuleFinishedEvent extends ApplicationEvent{
    private String moduleName;
    private ApplicationContext context;

    /**
     * Create a new ModuleFinishedEvent.
     *
     */
    public ModuleFinishedEvent(String moduleName,ApplicationContext context) {
        super(moduleName);
        this.context = context;
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
