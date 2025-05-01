package com.ai.langchain.service;

import reactor.core.publisher.Flux;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
public interface StreamChatAssistant {
    Flux<String> chat(String message);
}
