package com.ai.langchain.openai;

import com.ai.langchain.fuc.handles.InvoiceHandler;
import com.ai.langchain.service.*;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.*;
import dev.langchain4j.web.search.WebSearchTool;
import dev.langchain4j.web.search.searchapi.SearchApiWebSearchEngine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Supplier;

import static com.ai.langchain.openai.Properties.PREFIX;

@Configuration
@EnableConfigurationProperties(Properties.class)
public class OpenaiConfig {

    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    OpenAiChatModel openAiChatModel(Properties properties) {
        ChatModelProperties chatModelProperties = properties.chatModel();
        return OpenAiChatModel.builder()
            //.listeners(List.of(new CustomerChatModelListener()))
            .baseUrl(chatModelProperties.baseUrl())
            .apiKey(chatModelProperties.apiKey())
            .organizationId(chatModelProperties.organizationId())
            .modelName(chatModelProperties.modelName())
            .temperature(chatModelProperties.temperature())
            .topP(chatModelProperties.topP())
            .stop(chatModelProperties.stop())
            .maxTokens(chatModelProperties.maxTokens())
            .maxCompletionTokens(chatModelProperties.maxCompletionTokens())
            .presencePenalty(chatModelProperties.presencePenalty())
            .frequencyPenalty(chatModelProperties.frequencyPenalty())
            .logitBias(chatModelProperties.logitBias())
            .responseFormat(chatModelProperties.responseFormat())
            .strictJsonSchema(chatModelProperties.strictJsonSchema())
            .seed(chatModelProperties.seed())
            .user(chatModelProperties.user())
            .strictTools(chatModelProperties.strictTools())
            .parallelToolCalls(chatModelProperties.parallelToolCalls())
            .timeout(chatModelProperties.timeout())
            .maxRetries(chatModelProperties.maxRetries())
            .proxy(ProxyProperties.convert(chatModelProperties.proxy()))
            .logRequests(chatModelProperties.logRequests())
            .logResponses(chatModelProperties.logResponses())
            .customHeaders(chatModelProperties.customHeaders())
            .build();
    }
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
                .chatLanguageModel(openAiChatModel)
                .chatMemoryProvider(id -> MessageWindowChatMemory.withMaxMessages(10))
                .build()
                ;
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    FunctionAssistant functionAssistant(OpenAiChatModel openAiChatModel){
        return dev.langchain4j.service.AiServices
                .builder(FunctionAssistant.class)
                .chatLanguageModel(openAiChatModel)
                .tools(new InvoiceHandler())
                .build()
                ;
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".chat-model.api-key")
    WebsearchAssistant websearchAssistant(OpenAiChatModel openAiChatModel){
        SearchApiWebSearchEngine searchEngine = SearchApiWebSearchEngine.builder()
                .apiKey("a759fb82f3af64ccb")// 测试使用
                .engine("google")
                .build();

        return dev.langchain4j.service.AiServices
                .builder(WebsearchAssistant.class)
                .chatLanguageModel(openAiChatModel)
                .tools(new WebSearchTool(searchEngine))
                .build()
                ;
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".streaming-chat-model.api-key")
    OpenAiStreamingChatModel openAiStreamingChatModel(Properties properties) {
        ChatModelProperties chatModelProperties = properties.streamingChatModel();
        return OpenAiStreamingChatModel.builder()
            .baseUrl(chatModelProperties.baseUrl())
            .apiKey(chatModelProperties.apiKey())
            .organizationId(chatModelProperties.organizationId())
            .modelName(chatModelProperties.modelName())
            .temperature(chatModelProperties.temperature())
            .topP(chatModelProperties.topP())
            .stop(chatModelProperties.stop())
            .maxTokens(chatModelProperties.maxTokens())
            .maxCompletionTokens(chatModelProperties.maxCompletionTokens())
            .presencePenalty(chatModelProperties.presencePenalty())
            .frequencyPenalty(chatModelProperties.frequencyPenalty())
            .logitBias(chatModelProperties.logitBias())
            .responseFormat(chatModelProperties.responseFormat())
            .seed(chatModelProperties.seed())
            .user(chatModelProperties.user())
            .strictTools(chatModelProperties.strictTools())
            .parallelToolCalls(chatModelProperties.parallelToolCalls())
            .timeout(chatModelProperties.timeout())
            .proxy(ProxyProperties.convert(chatModelProperties.proxy()))
            .logRequests(chatModelProperties.logRequests())
            .logResponses(chatModelProperties.logResponses())
            .customHeaders(chatModelProperties.customHeaders())
            .build();
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".streaming-chat-model.api-key")
    StreamChatAssistant streamChatAssistant(OpenAiStreamingChatModel streamingChatModel){
        return dev.langchain4j.service.AiServices.create(StreamChatAssistant.class,streamingChatModel);
    }

    @Bean
    @ConditionalOnProperty(PREFIX + ".language-model.api-key")
    OpenAiLanguageModel openAiLanguageModel(Properties properties) {
        LanguageModelProperties languageModelProperties = properties.languageModel();
        return OpenAiLanguageModel.builder()
            .baseUrl(languageModelProperties.baseUrl())
            .apiKey(languageModelProperties.apiKey())
            .organizationId(languageModelProperties.organizationId())
            .modelName(languageModelProperties.modelName())
            .temperature(languageModelProperties.temperature())
            .timeout(languageModelProperties.timeout())
            .maxRetries(languageModelProperties.maxRetries())
            .proxy(ProxyProperties.convert(languageModelProperties.proxy()))
            .logRequests(languageModelProperties.logRequests())
            .logResponses(languageModelProperties.logResponses())
            .customHeaders(languageModelProperties.customHeaders())
            .build();
    }
    @Bean
    @ConditionalOnProperty(PREFIX + ".streaming-language-model.api-key")
    OpenAiStreamingLanguageModel openAiStreamingLanguageModel(Properties properties) {
        LanguageModelProperties languageModelProperties = properties.streamingLanguageModel();
        return OpenAiStreamingLanguageModel.builder()
            .baseUrl(languageModelProperties.baseUrl())
            .apiKey(languageModelProperties.apiKey())
            .organizationId(languageModelProperties.organizationId())
            .modelName(languageModelProperties.modelName())
            .temperature(languageModelProperties.temperature())
            .timeout(languageModelProperties.timeout())
            .proxy(ProxyProperties.convert(languageModelProperties.proxy()))
            .logRequests(languageModelProperties.logRequests())
            .logResponses(languageModelProperties.logResponses())
            .customHeaders(languageModelProperties.customHeaders())
            .build();
    }

    @Bean
    @ConditionalOnProperty(PREFIX + ".embedding-model.api-key")
    OpenAiEmbeddingModel openAiEmbeddingModel(Properties properties) {
        EmbeddingModelProperties embeddingModelProperties = properties.embeddingModel();
        return OpenAiEmbeddingModel.builder()
            .baseUrl(embeddingModelProperties.baseUrl())
            .apiKey(embeddingModelProperties.apiKey())
            .organizationId(embeddingModelProperties.organizationId())
            .modelName(embeddingModelProperties.modelName())
            .dimensions(embeddingModelProperties.dimensions())
            .user(embeddingModelProperties.user())
            .timeout(embeddingModelProperties.timeout())
            .maxRetries(embeddingModelProperties.maxRetries())
            .proxy(ProxyProperties.convert(embeddingModelProperties.proxy()))
            .logRequests(embeddingModelProperties.logRequests())
            .logResponses(embeddingModelProperties.logResponses())
            .customHeaders(embeddingModelProperties.customHeaders())
            .build();
    }

    @Bean
    @ConditionalOnProperty(PREFIX + ".image-model.api-key")
    OpenAiImageModel openAiImageModel(Properties properties) {
        ImageModelProperties imageModelProperties = properties.imageModel();
        return OpenAiImageModel.builder()
            .baseUrl(imageModelProperties.baseUrl())
            .apiKey(imageModelProperties.apiKey())
            .organizationId(imageModelProperties.organizationId())
            .modelName(imageModelProperties.modelName())
            .size(imageModelProperties.size())
            .quality(imageModelProperties.quality())
            .style(imageModelProperties.style())
            .user(imageModelProperties.user())
            .responseFormat(imageModelProperties.responseFormat())
            .timeout(imageModelProperties.timeout())
            .maxRetries(imageModelProperties.maxRetries())
            .proxy(ProxyProperties.convert(imageModelProperties.proxy()))
            .logRequests(imageModelProperties.logRequests())
            .logResponses(imageModelProperties.logResponses())
            .withPersisting(imageModelProperties.withPersisting())
            .persistTo(imageModelProperties.persistTo())
            .customHeaders(imageModelProperties.customHeaders())
            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    OpenAiTokenizer openAiTokenizer(Properties properties) {
        String modelName = null;
        modelName = getModelName(modelName,properties,() ->{
            return Optional.ofNullable(properties.chatModel()).map(m->m.modelName()).orElse(null);
        });
        modelName = getModelName(modelName,properties,() ->{
            return Optional.ofNullable(properties.streamingChatModel()).map(m->m.modelName()).orElse(null);
        });
        modelName = getModelName(modelName,properties,() ->{
            return Optional.ofNullable(properties.languageModel()).map(m->m.modelName()).orElse(null);
        });
        modelName = getModelName(modelName,properties,() ->{
            return Optional.ofNullable(properties.streamingLanguageModel()).map(m->m.modelName()).orElse(null);
        });
        modelName = getModelName(modelName,properties,() ->{
            return Optional.ofNullable(properties.embeddingModel()).map(m->m.modelName()).orElse(null);
        });
        modelName = getModelName(modelName,properties,() ->{
            return Optional.ofNullable(properties.imageModel()).map(m->m.modelName()).orElse(null);
        });
        return new OpenAiTokenizer(modelName);
    }

    private String getModelName(String modelName, Properties properties, Supplier<String> supplier){
        modelName = Optional.ofNullable(modelName).orElseGet(()->{
            return Optional.ofNullable(properties)
                .map(p -> supplier.get())
                .orElse(null);
        });
        return modelName;
    }
}
