package com.ai.langchain.test;

import com.ai.langchain.service.MemoryChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@SpringBootTest
public class MemoryChatTest {
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    @Test
    public void testChatAssistant(){
        Long userId = 1L;
        String text = memoryChatAssistant.chat(userId,"你好，我的名字叫冷峰, 我喜欢吃苹果。");
        System.out.println(text);
        text = memoryChatAssistant.chat(2L,"你好，我的名字叫春风, 我喜欢吃香蕉。");
        System.out.println(text);

        text = memoryChatAssistant.chat(userId,"你知道我喜欢吃的水果是什么吗。");
        System.out.println(text);

        text = memoryChatAssistant.chat(2L,"你知道我喜欢吃的水果是什么吗。");
        System.out.println(text);
    }
}
