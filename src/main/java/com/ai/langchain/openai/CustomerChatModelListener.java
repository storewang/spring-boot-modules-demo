package com.ai.langchain.openai;

import dev.langchain4j.model.chat.listener.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class CustomerChatModelListener implements ChatModelListener {
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        ChatModelRequest request = requestContext.request();
        Map<Object, Object> attributes = requestContext.attributes();
        // 记录请求
        log.info("请求:{}",attributes);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        ChatModelResponse response = responseContext.response();
        ChatModelRequest request = responseContext.request();
        Map<Object, Object> attributes = responseContext.attributes();
        // 记录响应
        log.info("响应:{}",attributes);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        Throwable error = errorContext.error();
        ChatModelRequest request = errorContext.request();
        ChatModelResponse partialResponse = errorContext.partialResponse();
        Map<Object, Object> attributes = errorContext.attributes();
        // 记录错误
        log.warn("错误:{}",attributes);
    }
}
