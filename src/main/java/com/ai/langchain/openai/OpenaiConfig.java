package com.ai.langchain.openai;

import com.ai.langchain.fuc.handles.InvoiceHandler;
import com.ai.langchain.service.*;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.*;
import dev.langchain4j.openai.spring.AutoConfig;
import dev.langchain4j.openai.spring.Properties;
import dev.langchain4j.service.tool.ToolProvider;
import dev.langchain4j.web.search.WebSearchTool;
import dev.langchain4j.web.search.searchapi.SearchApiWebSearchEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(Properties.class)
@AutoConfigureAfter(AutoConfig.class)
public class OpenaiConfig {
    static final String PREFIX = "langchain4j.open-ai";
    @Value("${langchain4j.open-ai.websearch.api-key}")
    private String google_web_key;
    @Value("${langchain4j.open-ai.mcp.sse-url}")
    private String mcp_sse_url;

    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    ChatAssistant chartAssistant(OpenAiChatModel openAiChatModel){
       return dev.langchain4j.service.AiServices.create(ChatAssistant.class,openAiChatModel);
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    MemoryChatAssistant memoryChatAssistant(OpenAiChatModel openAiChatModel){
        return dev.langchain4j.service.AiServices
                .builder(MemoryChatAssistant.class)
                .chatModel(openAiChatModel)
                .chatMemoryProvider(id -> MessageWindowChatMemory.withMaxMessages(10))
                .build()
                ;
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    FunctionAssistant functionAssistant(OpenAiChatModel openAiChatModel){
        return dev.langchain4j.service.AiServices
                .builder(FunctionAssistant.class)
                //.chatLanguageModel(openAiChatModel)
                .chatModel(openAiChatModel)
                .tools(new InvoiceHandler())
                .build()
                ;
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".mcp.sse-url")
    McpTransport mcpTransport(){
        return new HttpMcpTransport.Builder()
                .sseUrl(mcp_sse_url)
                .logRequests(true)
                .logResponses(true)
                .build();
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".mcp.sse-url")
    McpClient mcpClient(McpTransport mcpTransport){
        return new DefaultMcpClient.Builder()
                .transport(mcpTransport)
                .build();
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".mcp.sse-url")
    McpFuncAssistant mcpFuncAssistant(McpClient mcpClient,OpenAiChatModel openAiChatModel){
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClient))
                .build();
        return dev.langchain4j.service.AiServices
                .builder(McpFuncAssistant.class)
                .chatModel(openAiChatModel)
                .toolProvider(toolProvider)
                .build()
                ;
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    WebsearchAssistant websearchAssistant(OpenAiChatModel openAiChatModel){
        SearchApiWebSearchEngine searchEngine = SearchApiWebSearchEngine.builder()
                .apiKey(google_web_key)// 测试使用
                .engine("google")
                .build();

        return dev.langchain4j.service.AiServices
                .builder(WebsearchAssistant.class)
                .chatModel(openAiChatModel)
                .tools(new WebSearchTool(searchEngine))
                .build()
                ;
    }

    @Bean
    @ConditionalOnProperty(PREFIX + ".streaming-chat-model.api-key")
    StreamChatAssistant streamChatAssistant(OpenAiStreamingChatModel streamingChatModel){
        return dev.langchain4j.service.AiServices.create(StreamChatAssistant.class,streamingChatModel);
    }

}
