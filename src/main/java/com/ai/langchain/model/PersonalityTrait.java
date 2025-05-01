package com.ai.langchain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@Getter
@AllArgsConstructor
public enum PersonalityTrait {
    /**
     * 外向型：喜欢社交，从与他人互动中获得能量
     */
    EXTROVERT,

    /**
     * 内向型：倾向于独处，需要安静时间来恢复能量
     */
    INTROVERT,

    /**
     * 分析型：擅长逻辑思考，喜欢解决复杂问题
     */
    ANALYTICAL,

    /**
     * 创意型：富有想象力，常有新颖的想法
     */
    CREATIVE,

    /**
     * 领导型：善于指导他人，乐于承担责任
     */
    LEADER,

    /**
     * 团队合作型：重视协作，善于在团队中工作
     */
    TEAM_PLAYER
}
