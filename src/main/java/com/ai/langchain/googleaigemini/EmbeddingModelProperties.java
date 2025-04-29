package com.ai.langchain.googleaigemini;

import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import lombok.Data;

import java.time.Duration;

@Data
public class EmbeddingModelProperties {
    private String apiKey;
    private String modelName;
    private String titleMetadataKey;
    private Boolean logRequestsAndResponses;
    private Integer maxRetries;
    private Integer outputDimensionality;
    private GoogleAiEmbeddingModel.TaskType taskType;
    private Duration timeout;
}
