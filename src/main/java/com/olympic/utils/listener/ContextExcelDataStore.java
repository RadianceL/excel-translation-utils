package com.olympic.utils.listener;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.olympic.config.ConfigContext;
import com.olympic.translation.TransApi;

import java.util.*;

/**
 * 上下文excel存储
 *
 * @author eddie.lys
 * @since 2023/4/23
 */
public class ContextExcelDataStore {


    private static final Map<Integer, Map<Integer, List<String>>> contextMap = new HashMap<>();
    private static final Map<Integer, Map<Integer, List<String>>> transMap = new HashMap<>();

    public static void addContextExcelData(Integer sheetIndex, Map<Integer, List<String>> dataMap) {
        contextMap.put(sheetIndex, dataMap);
    }

    public static Map<Integer, List<String>> getExcelMap(Integer sheetIndex) {
        return contextMap.get(sheetIndex);
    }

    public static void initHandle(String filePath) {
        TransApi transApi = new TransApi(ConfigContext.getConfig(ConfigContext.BAIDU_APP_AEY), ConfigContext.getConfig(ConfigContext.BAIDU_APP_SECRET));

        contextMap.forEach((sheetNo, dataLine) -> {
            transMap.put(sheetNo, new HashMap<>());
            dataLine.forEach((fieldNo, messageList) -> {
                transMap.get(sheetNo).put(fieldNo, new ArrayList<>());
                messageList.forEach(message -> {
                            try {
                                if (StringUtils.isBlank(message)) {
                                    return;
                                }
                                String transResult = transApi.getTransResult(message, "zh", "en", 200);
                                JSONObject transResultJson = JSON.parseObject(transResult);
                                String dst = transResultJson.getJSONArray("trans_result").getJSONObject(0).getString("dst");
                                System.out.println("from -> " + message + ", to -> " + dst);
                                transMap.get(sheetNo).get(fieldNo).add(dst);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    );
                }
            );
        });
        writeToExcel(filePath);
    }

    private static void writeToExcel(String filePath) {
        Set<Integer> includeColumnFiledNames = new HashSet<>();
        transMap.forEach((sheetNo, dataLine) -> dataLine.forEach((fieldNo, messageList) -> {
//            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//            includeColumnFiledNames.add(fieldNo);
//            EasyExcel.write().withTemplate(filePath).includeColumnIndexes(includeColumnFiledNames)
//                    .relativeHeadRowIndex(8)
//                    .sheet(sheetNo)
//                    .doWrite(messageList);
            messageList.forEach(System.out::println);
        }));

    }
}
