package com.olympic;

import com.olympic.config.ConfigContext;
import com.olympic.utils.ExcelReaderUtils;

import java.util.Arrays;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author eddie.lys
 * @since 2023/4/23
 */
public class Main {

    /**
     * 百度翻译KEY
     */
    private static final String apiKey = "";
    /**
     * 百度翻译secret
     */
    private static final String apiSecret = "";

    private static final String filePath = "/Users/eddie/Desktop/契約品翻訳リスト.xlsx";

    /**
     * IF java 11+ vm option
     * --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED
     */
    public static void main(String[] args) {
        ConfigContext.setConfig(ConfigContext.BAIDU_APP_AEY, apiKey);
        ConfigContext.setConfig(ConfigContext.BAIDU_APP_SECRET, apiSecret);
        ExcelReaderUtils.readExcel(filePath, List.of(3), List.of(1));
    }
}