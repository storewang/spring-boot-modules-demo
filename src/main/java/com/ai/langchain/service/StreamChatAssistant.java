package com.ai.langchain.service;

import com.ai.langchain.model.LegalPrompt;
import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
public interface StreamChatAssistant {
    Flux<String> chat(String message);

    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    Flux<String> chat4Legal(LegalPrompt prompt);
}
