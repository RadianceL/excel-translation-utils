package com.olympic.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置上下文
 *
 * @author eddie.lys
 * @since 2023/4/23
 */
public class ConfigContext {
    /**
     * 配置容器
     */
    private final static Map<String, String> configMap = new HashMap<>();
    /**
     * 配置KEY - 百度APP_KEY
     */
    public static final String BAIDU_APP_AEY = "BD_APP_KEY";
    /**
     * 配置KEY - 百度APP_SECRET
     */
    public static final String BAIDU_APP_SECRET = "BD_APP_SECRET";

    public static void setConfig(String key, String value) {
        configMap.put(key, value);
    }

    public static String getConfig(String key) {
        return configMap.get(key);
    }
}
