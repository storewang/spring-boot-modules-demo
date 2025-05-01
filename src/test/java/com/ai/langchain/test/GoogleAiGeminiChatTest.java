package com.ai.langchain.test;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@SpringBootTest
public class GoogleAiGeminiChatTest {
    @Autowired
    private GoogleAiGeminiChatModel googleAiGeminiChatModel;
    @Test
    public void testGeminiChat(){
        ChatMessage systemMessage = new SystemMessage("你是一个英文翻译助手");
        ChatMessage aiMessage = new UserMessage("翻译下面这个句子: 春风十里不如你");
        ChatRequest request = ChatRequest.builder()
                .messages(systemMessage,aiMessage)
                .build();
        ChatResponse chatResponse = googleAiGeminiChatModel.chat(request);
        System.out.println(chatResponse.aiMessage().text());
    }
}
