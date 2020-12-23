package com.ai.spring.sofa.test;

import com.alipay.sofa.rpc.config.ProviderConfig;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/31
 * @Version 1.0
 **/

public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String string) {
        ProviderConfig<?> providerConfig;
        System.out.println("Server receive: " + string);
        return "hello " + string + " ！";
    }
}
