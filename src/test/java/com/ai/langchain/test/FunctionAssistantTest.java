package com.ai.langchain.test;

import com.ai.langchain.service.FunctionAssistant;
import com.ai.langchain.service.McpFuncAssistant;
import com.ai.langchain.service.WebsearchAssistant;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.mcp.client.McpClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private McpClient mcpClient;
    @Autowired
    private McpFuncAssistant mcpFuncAssistant;

    @Test
    public void testFuncAssistant(){
        String text = functionAssistant.chat("帮我开具一张发票， 公司：石头科技有限公司 税号：ZXC123 金额： 1000.1245");
        System.out.println(text);
    }
    @Test
    public void testFuncAssistant2(){
        String text = functionAssistant.chat("公司这个月的销售金额为100.98万，公司名称为石头科技有限公司，税号为ZXC123 , 帮我开具一张这个月的发票");
        System.out.println(text);
    }

    @Test
    public void testWebsearchAssistant(){
        String text = websearchAssistant.chat("今天20250429 上证指数是多少？");
        System.out.println(text);
    }

    @Test
    public void testMcpClient(){
        List<ToolSpecification> toolSpecifications = mcpClient.listTools();
        String tools = toolSpecifications.stream().map(it -> it.name()).collect(Collectors.joining(","));
        System.out.println(tools);
    }
    @Test
    public void testMcpFuncAssistant(){
        String chat = mcpFuncAssistant.chat("帮我查看下redis中的yuxiaor:risk:whiteList:1这个key的值");
        System.out.println("值为: "+chat);
    }
}
