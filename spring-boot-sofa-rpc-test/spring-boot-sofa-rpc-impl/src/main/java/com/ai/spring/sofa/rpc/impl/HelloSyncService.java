package com.ai.spring.sofa.rpc.impl;

import com.ai.spring.sofa.rpc.api.IHelloService;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/9/5
 * @Version 1.0
 **/
@SofaService(interfaceType = IHelloService.class,bindings = {@SofaServiceBinding(bindingType = "bolt",filters = {"customer"})})
@Service
@Slf4j
public class HelloSyncService implements IHelloService {
    @Override
    public String saySync(String message) {
        log.info("------saySync:{}-----------",message);
        return message;
    }
}
