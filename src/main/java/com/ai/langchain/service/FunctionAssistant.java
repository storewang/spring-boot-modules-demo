package com.ai.langchain.service;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
public interface FunctionAssistant {
    /**
     * 带函数调用的助手
     * 用户提问： 帮我开具发票，开票信息是 ：公司名称xxx 税号 xx 金额
     * @param message
     * @return
     */
    String chat(String message);
}
