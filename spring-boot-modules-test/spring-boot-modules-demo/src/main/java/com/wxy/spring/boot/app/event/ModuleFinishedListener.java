package com.wxy.spring.boot.app.event;

import com.wxy.spring.boot.app.conf.ModuleApplicationContext;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * 子模块启动完成监听
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
public class ModuleFinishedListener implements ApplicationListener<ModuleFinishedEvent> {
    private List<String> modules;
    public ModuleFinishedListener(List<String> modules){
        this.modules = modules;
    }

    @Override
    public void onApplicationEvent(ModuleFinishedEvent event) {
        System.out.println("------收到消息：-----------"+event.getModuleName());
        modules.remove(event.getModuleName());
        // 全部启动完成
        if (modules.isEmpty()){
            ModuleApplicationContext.resolveLazyDependencys();
        }
    }
}
