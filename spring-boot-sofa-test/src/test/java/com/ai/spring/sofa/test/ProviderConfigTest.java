package com.ai.spring.sofa.test;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/7/31
 * @Version 1.0
 **/

public class ProviderConfigTest {
    public static void main(String[] args) {
        ServerConfig serviceConfig = new ServerConfig();
        serviceConfig.setProtocol("bolt");
        serviceConfig.setPort(12200);
        serviceConfig.setDaemon(false);

        ProviderConfig<HelloService> providerConfig = new ProviderConfig<>();
        providerConfig.setInterfaceId(HelloService.class.getName());
        providerConfig.setRef(new HelloServiceImpl()).setServer(serviceConfig);
        providerConfig.export();
    }
}

