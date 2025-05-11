package com.ai.langchain.test;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Base64;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@SpringBootTest
public class OpenAi4ImageTest {
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Value("static/langchain4j.png")
    private Resource resource;

    @Test
    public void testOcrImage() throws IOException {
        byte[] byteArray = resource.getContentAsByteArray();
        String encodeToString = Base64.getEncoder().encodeToString(byteArray);

        UserMessage userMessage = UserMessage.from(
                TextContent.from("提取图片中 9月30号的上证指数点数")
                ,
                ImageContent.from(encodeToString, "image/png")
        );
        ChatResponse response = openAiChatModel.chat(userMessage);
        System.out.println(response.aiMessage().text());
    }
}
