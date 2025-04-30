package com.ai.langchain.openai;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.time.Duration;
import java.util.Map;

public record ModerationModelProperties(
    String baseUrl,
    String apiKey,
    String organizationId,
    String modelName,
    Duration timeout,
    Integer maxRetries,
    @NestedConfigurationProperty
    ProxyProperties proxy,
    Boolean logRequests,
    Boolean logResponses,
    Map<String, String> customHeaders
) {
}
