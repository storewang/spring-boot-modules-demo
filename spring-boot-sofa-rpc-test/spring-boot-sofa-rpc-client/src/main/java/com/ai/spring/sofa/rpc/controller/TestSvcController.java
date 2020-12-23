package com.ai.spring.sofa.rpc.controller;

import com.ai.spring.sofa.rpc.api.IHelloService;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/9/5
 * @Version 1.0
 **/
@RestController
public class TestSvcController {
    @SofaReference(interfaceType = IHelloService.class,binding = @SofaReferenceBinding(bindingType = "bolt"))
    private IHelloService helloService;
    @GetMapping("/say")
    public String saySync(String msg){
        return helloService.saySync(msg);
    }
}
