package com.ai.langchain.googleaigemini;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.util.Map;

@Data
@Accessors(chain = true)
public class ChatModelProperties {
    private String apiKey;
    private String modelName;
    private Double temperature;
    private Double topP;
    private Integer topK;
    private Integer maxOutputTokens;
    private Boolean logRequestsAndResponses;
    private Integer maxRetries;
    private Duration timeout;
    private Map<String,String> safetySetting;
    private GeminiFunctionCallingConfig functionCallingConfig;
}
