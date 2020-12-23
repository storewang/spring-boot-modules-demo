package com.ai.spring.sofa.rpc.filter;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.ext.Extension;
import com.alipay.sofa.rpc.filter.AutoActive;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * rpc调用拦截器
 *
 * @author 石头
 * @Date 2019/9/5
 * @Version 1.0
 **/
//@Extension("customer")
//@AutoActive(providerSide = true)
@Slf4j
@Component("customer")
public class CustomFilter extends Filter{
    @Override
    public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {
        log.info("------{}.{}-----rpc-invoke start.--",request.getTargetAppName(),request.getMethodName());
        SofaResponse response = invoker.invoke(request);
        log.info("------{}.{}-----rpc-invoke end.--",request.getTargetAppName(),request.getMethodName());
        return response;
    }
}
