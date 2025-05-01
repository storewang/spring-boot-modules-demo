package com.ai.langchain.controller;

import com.ai.langchain.service.StreamChatAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoController {
    private final StreamChatAssistant chatAssistant;

    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam("message") String message) {
        return chatAssistant.chat(message);
    }
}
