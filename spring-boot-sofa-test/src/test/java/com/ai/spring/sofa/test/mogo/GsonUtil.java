package com.ai.spring.sofa.test.mogo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author 石头
 *         Created by 石头 on 2018/10/23.
 * @date 2018/10/23.
 */
public class GsonUtil {
    public static final GsonBuilder INSTANCE = new GsonBuilder();
    static {
        INSTANCE.disableHtmlEscaping();
    }
    public static Gson create() {
        return INSTANCE.create();
    }
}
