package com.ai.langchain.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoogleAiGeminiChatModelTest implements CommandLineRunner {
    @Autowired
    private GoogleAiGeminiChatModel googleAiGeminiChatModel;

    public void testGeminiChat(){
        ChatMessage systemMessage = new SystemMessage("你是一个中文翻译助手");
        ChatMessage aiMessage = new AiMessage("翻译下面这个句子: You are the fairest of them all");
        ChatRequest request = ChatRequest.builder()
            .messages(systemMessage,aiMessage)
            .build();
        ChatResponse chatResponse = googleAiGeminiChatModel.chat(request);
        String text = chatResponse.aiMessage().text();
        log.warn("ai msg rs: {}",text);
    }

    @Override
    public void run(String... args) throws Exception {
        testGeminiChat();
    }
}
