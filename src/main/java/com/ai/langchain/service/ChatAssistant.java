package com.ai.langchain.service;

import com.ai.langchain.model.LegalPrompt;
import dev.langchain4j.model.output.structured.Description;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
public interface ChatAssistant {
    /**
     * 聊天
     * @param message
     * @return
     */
    String chat(String message);

    /**
     * 法律助手
     * @param question
     * @return
     */
    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    @UserMessage("请回答以下法律问题：{{question}}")
    String chat4Legal(@V("question") String question);

    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    String chat4Legal(LegalPrompt prompt);

    @UserMessage("Extract a number from {{it}}")
    int extractInt(String text);

    @UserMessage("{{it}} 是否具有正面情感？")
    boolean isPositive(String text);
    @UserMessage("分析 {{it}} 的情感")
    Sentiment analyzeSentimentOf(String text);

    @UserMessage("Extract information about a person from {{it}}")
    Person extractPerson(String text);

    enum Sentiment {
        POSITIVE, // 正面情感
        NEGATIVE, // 负面情感
        NEUTRAL   // 中立情感
    }

    @Data
    class Person {
        @Description("name of a person") // 增加字段描述，让大模型更理解字段含义
        private String name;
        private LocalDate birthDate;
    }
}
