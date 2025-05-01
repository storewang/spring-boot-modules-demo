package com.ai.langchain.model;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}")
public class LegalPrompt {
    private String legal;
    private String question;
}
