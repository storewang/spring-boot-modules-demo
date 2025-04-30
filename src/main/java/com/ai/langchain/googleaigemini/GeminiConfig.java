package com.ai.langchain.googleaigemini;

import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.googleai.*;
import dev.langchain4j.model.googleai.GeminiFunctionCallingConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ai.langchain.googleaigemini.Properties.PREFIX;

@Configuration
@EnableConfigurationProperties(Properties.class)
public class GeminiConfig {

    @Bean
    @ConditionalOnProperty({
        PREFIX + ".chat-model.api-key",
        PREFIX + ".chat-model.model-name"
    })
    GoogleAiGeminiChatModel googleAiGeminiChatModel(Properties properties, ObjectProvider<ChatModelListener> listeners){
        ChatModelProperties chatModelProperties = properties.getChatModel();
        GoogleAiGeminiChatModel.GoogleAiGeminiChatModelBuilder builder = GoogleAiGeminiChatModel.builder()
            .apiKey(chatModelProperties.getApiKey())
            .modelName(chatModelProperties.getModelName())
            .maxRetries(chatModelProperties.getMaxRetries())
            .temperature(chatModelProperties.getTemperature())
            .topK(chatModelProperties.getTopK())
            .topP(chatModelProperties.getTopP())
            .maxOutputTokens(chatModelProperties.getMaxOutputTokens())
            .timeout(chatModelProperties.getTimeout())
            .logRequestsAndResponses(chatModelProperties.getLogRequestsAndResponses())
            .listeners(listeners.orderedStream().collect(Collectors.toList()));
        if (chatModelProperties.getSafetySetting() != null && !chatModelProperties.getSafetySetting().isEmpty()) {
            builder.safetySettings(convertSafetySettings(chatModelProperties.getSafetySetting()));
        }
        if (chatModelProperties.getFunctionCallingConfig() != null) {
            builder.toolConfig(chatModelProperties
                    .getFunctionCallingConfig()
                    .getGeminiMode(),
                chatModelProperties
                    .getFunctionCallingConfig()
                    .getAllowedFunctionNames()
                    .toArray(new String[0]));
        }
        return builder.build();
    }
    @Bean
    @ConditionalOnProperty({
        PREFIX + ".streaming-chat-model.api-key",
        PREFIX + ".streaming-chat-model.model-name"
    })
    GoogleAiGeminiStreamingChatModel googleAiGeminiStreamingChatModel(Properties properties, ObjectProvider<ChatModelListener> listeners){
        ChatModelProperties chatModelProperties = properties.getStreamingChatModel();
        GoogleAiGeminiStreamingChatModel.GoogleAiGeminiStreamingChatModelBuilder builder = GoogleAiGeminiStreamingChatModel.builder()
            .apiKey(chatModelProperties.getApiKey())
            .modelName(chatModelProperties.getModelName())
            .temperature(chatModelProperties.getTemperature())
            .topK(chatModelProperties.getTopK())
            .topP(chatModelProperties.getTopP())
            .maxOutputTokens(chatModelProperties.getMaxOutputTokens())
            .timeout(chatModelProperties.getTimeout())
            .logRequestsAndResponses(chatModelProperties.getLogRequestsAndResponses())
            .listeners(listeners.orderedStream().collect(Collectors.toList()))
            .maxRetries(chatModelProperties.getMaxRetries());
        if (chatModelProperties.getSafetySetting() != null && !chatModelProperties.getSafetySetting().isEmpty()) {
            List<GeminiSafetySetting> geminiSafetySettings = convertSafetySettings(chatModelProperties.getSafetySetting()).entrySet().stream().map((entry) -> {
                return new GeminiSafetySetting(entry.getKey(),entry.getValue());
            }).collect(Collectors.toList());
            builder.safetySettings(geminiSafetySettings);
        }
        if (chatModelProperties.getFunctionCallingConfig() != null) {
            GeminiFunctionCallingConfig config = new GeminiFunctionCallingConfig(chatModelProperties
                .getFunctionCallingConfig()
                .getGeminiMode(), Arrays.asList(chatModelProperties
                .getFunctionCallingConfig()
                .getAllowedFunctionNames()
                .toArray(new String[0])));
            builder.toolConfig(config);
        }
        return builder.build();
    }

    @Bean
    @ConditionalOnProperty({
        PREFIX + ".embedding-model.api-key",
        PREFIX + ".embedding-model.model-name"
    })
    GoogleAiEmbeddingModel googleAiEmbeddingModel(Properties properties){
        EmbeddingModelProperties embeddingModelProperties = properties.getEmbeddingModel();
        return GoogleAiEmbeddingModel.builder()
            .modelName(embeddingModelProperties.getModelName())
            .apiKey(embeddingModelProperties.getApiKey())
            .maxRetries(embeddingModelProperties.getMaxRetries())
            .taskType(embeddingModelProperties.getTaskType())
            .titleMetadataKey(embeddingModelProperties.getTitleMetadataKey())
            .outputDimensionality(embeddingModelProperties.getOutputDimensionality())
            .timeout(embeddingModelProperties.getTimeout())
            .logRequestsAndResponses(embeddingModelProperties.getLogRequestsAndResponses())
            .build();
    }
    private Map<GeminiHarmCategory, GeminiHarmBlockThreshold> convertSafetySettings(Map<String, String> map) {
        return map.entrySet().stream()
            .collect(Collectors.toMap(
                e -> GeminiHarmCategory.valueOf(e.getKey()),
                e -> GeminiHarmBlockThreshold.valueOf(e.getValue())
            ));
    }
}
