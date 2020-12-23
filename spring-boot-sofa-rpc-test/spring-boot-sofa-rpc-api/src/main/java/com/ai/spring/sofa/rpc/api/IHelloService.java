package com.ai.spring.sofa.rpc.api;

/**
 * 测试服务接口
 *
 * @author 石头
 * @Date 2019/9/5
 * @Version 1.0
 **/
public interface IHelloService {
    /**
     * 测试服务
     * @param message
     * @return
     */
    String saySync(String message);
}
