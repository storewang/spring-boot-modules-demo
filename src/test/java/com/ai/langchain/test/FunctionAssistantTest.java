package com.ai.langchain.test;

import com.ai.langchain.service.FunctionAssistant;
import com.ai.langchain.service.WebsearchAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@SpringBootTest
public class FunctionAssistantTest {
    @Autowired
    private FunctionAssistant functionAssistant;
    @Autowired
    private WebsearchAssistant websearchAssistant;

    @Test
    public void testFuncAssistant(){
        String text = functionAssistant.chat("帮我开具一张发票， 公司：石头科技有限公司 税号：ZXC123 金额： 1000.1245");
        System.out.println(text);
    }

    @Test
    public void testWebsearchAssistant(){
        String text = websearchAssistant.chat("今天20250429 上证指数是多少？");
        System.out.println(text);
    }
}
