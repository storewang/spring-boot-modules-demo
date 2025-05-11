package com.ai.langchain.service;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/11
 **/
public interface McpFuncAssistant {
    /**
     * 带mcp调用的助手
     * @param message
     * @return
     */
    String chat(String message);
}
