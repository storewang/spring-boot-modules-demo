package com.ai.spring.sofa.test;

import com.alipay.sofa.rpc.config.ConsumerConfig;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/31
 * @Version 1.0
 **/

public class QuickStartClient {
    public static void main(String[] args) {
        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setInterfaceId(HelloService.class.getName())
                .setProtocol("bolt")
                .setDirectUrl("bolt://127.0.0.1:12200");
        HelloService helloService = consumerConfig.refer();
        while (true){
            System.out.println(helloService.sayHello("world"));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
    }
}
