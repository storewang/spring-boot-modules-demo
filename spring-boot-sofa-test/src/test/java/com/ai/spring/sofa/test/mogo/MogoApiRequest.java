package com.ai.spring.sofa.test.mogo;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 石头
 * @Date 2019/6/20
 * @Version 1.0
 **/
public interface MogoApiRequest<T extends MogoApiResponse> extends Serializable {
    /**
     * 获取api方法名
     * @return api方法名
     */
    String getApiMethodName();

    /**
     * 获取响应class
     * @return 响应class
     */
    Class<T> getResponseClass();

}
