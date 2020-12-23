package com.wxy.spring.boot.app.controller;

import com.wxy.spring.boot.app.conf.ModuleApplicationContext;
import com.wxy.spring.boot.modules.api.bus.IBusniseService;
import com.wxy.spring.boot.modules.api.bus.dto.BusinessDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 业务服务controller
 *
 * @author 石头
 * @Date 2020/12/22
 * @Version 1.0
 **/
@RestController
public class BusinessController implements InitializingBean {
    private IBusniseService busniseService;

    @GetMapping(path = "/get/{userId}")
    public ResponseEntity<BusinessDTO> findByUserId(@PathVariable("userId")Long userId){
        BusinessDTO businessDTO = busniseService.findByUserId(userId);
        return ResponseEntity.ok(businessDTO);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //busniseService = ModuleApplicationContext.getModuleApplication("bus-service").getBean(IBusniseService.class);
        //busniseService = (IBusniseService)ModuleApplicationContext.getBean("com.wxy.spring.boot.modules.demo.bus.BusniseService");
        ModuleApplicationContext.LazyDependency lazyDependency = new ModuleApplicationContext.LazyDependency();
        lazyDependency.setModuleName("main-application");
        lazyDependency.setBeanName("businessController");
        lazyDependency.setFieldName("busniseService");
        lazyDependency.setDependencyName("com.wxy.spring.boot.modules.demo.bus.BusniseService");

        ModuleApplicationContext.addLasyDependency(lazyDependency);
    }
}
