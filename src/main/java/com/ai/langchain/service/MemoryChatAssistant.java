package com.ai.langchain.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
public interface MemoryChatAssistant {
    /**
     * 聊天
     * @param userId  用户ID
     * @param message 消息
     * @return
     */
    String chat(@MemoryId Long userId, @UserMessage String message);
}
