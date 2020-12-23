package com.ai.spring.sofa.rpc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 启动类
 *
 * @author 石头
 * @Date 2019/9/5
 * @Version 1.0
 **/
@SpringBootApplication
public class SpringBootRpcClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringBootRpcClientApplication.class).run(args);
    }
}
