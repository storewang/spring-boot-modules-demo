package com.ai.langchain.test;

import com.ai.langchain.model.LegalPrompt;
import com.ai.langchain.service.ChatAssistant;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@SpringBootTest
public class OpenAiChatTest {
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Autowired
    private ChatAssistant chatAssistant;

    @Test
    public void testChatAssistant(){
        String text = chatAssistant.chat("翻译下面这个句子成英文: 春风十里不如你");
        System.out.println(text);
    }
    @Test
    public void testChat4Legal(){
        String text = chatAssistant.chat4Legal("什么是著作权？");
        System.out.println(text);

        text = chatAssistant.chat4Legal("上海有哪些区");
        System.out.println(text);
    }
    @Test
    public void testChat4Legal2(){
        LegalPrompt prompt = new LegalPrompt();
        prompt.setLegal("著作权");
        prompt.setQuestion("LGPL协议是什么？");

        String text = chatAssistant.chat4Legal(prompt);
        System.out.println(text);

        prompt.setQuestion("上海有哪些区？");
        text = chatAssistant.chat4Legal(prompt);
        System.out.println(text);
    }
    @Test
    public void testOpenaiChat(){
        ChatMessage systemMessage = new SystemMessage("你是一个中文翻译助手");
        ChatMessage aiMessage = new UserMessage("翻译下面这个句子: You are the fairest of them all");
        ChatRequest request = ChatRequest.builder()
                .messages(systemMessage,aiMessage)
                .build();
        ChatResponse chatResponse = openAiChatModel.chat(request);
        String text = chatResponse.aiMessage().text();
        System.out.println(text);
    }

    @Test
    public void testExtractInt(){
        String msg = "我今天赚了五块钱";
        int rs = chatAssistant.extractInt(msg);
        Assertions.assertEquals(rs,5);
        System.out.println(rs);
        msg = "我今天赚了10块钱";
        rs = chatAssistant.extractInt(msg);
        Assertions.assertEquals(rs,10);
        System.out.println(rs);
    }

    @Test
    public void testIsPositive(){
        boolean positive = chatAssistant.isPositive("假期结束开始上班");
        System.out.println(positive);
    }

    @Test
    public void testAnalyzeSentimentOf(){
        ChatAssistant.Sentiment positive = chatAssistant.analyzeSentimentOf("假期结束开始上班");
        System.out.println(positive);
    }

    @Test
    public void testExtractPerson(){
        ChatAssistant.Person person = chatAssistant.extractPerson("帮我创建一个用户，名字叫张三，生日是1990-01-01");
        System.out.println(person);
    }
}
