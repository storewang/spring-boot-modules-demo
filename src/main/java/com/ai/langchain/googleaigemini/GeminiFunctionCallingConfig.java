package com.ai.langchain.googleaigemini;

import dev.langchain4j.model.googleai.GeminiMode;
import lombok.Data;

import java.util.List;

@Data
public class GeminiFunctionCallingConfig {
    private GeminiMode geminiMode;
    private List<String> allowedFunctionNames;
}
