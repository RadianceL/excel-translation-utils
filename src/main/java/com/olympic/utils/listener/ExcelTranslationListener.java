package com.olympic.utils.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import org.apache.poi.util.StringUtil;

import java.util.*;

/**
 * 翻译监听器
 *
 * @author eddie.lys
 * @since 2023/4/23
 */
public class ExcelTranslationListener implements ReadListener<Map<Integer, String>> {

    private final Map<Integer, Map<Integer, List<String>>> saveData = new HashMap<>();

    private final List<Integer> targetIndex;

    static {

    }

    public ExcelTranslationListener(List<Integer> targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        System.out.println(("解析到一条数据:" + JSON.toJSONString(data)));
        Integer sheetNo = context.readSheetHolder().getReadSheet().getSheetNo();
        Map<Integer, List<String>> dataLineMap = saveData.get(sheetNo);
        if (Objects.isNull(dataLineMap)) {
            saveData.put(sheetNo, new HashMap<>());
        }
        for (Integer index : targetIndex) {
            String message = data.get(index);
            if (StringUtils.isNotBlank(message)) {
                message = message.replace("% s", "%s");
            }
            if (Objects.isNull(saveData.get(sheetNo).get(index))) {
                saveData.get(sheetNo).put(index, new ArrayList<>());
            }

            saveData.get(sheetNo).get(index).add(message);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData.forEach(ContextExcelDataStore::addContextExcelData);
        ContextExcelDataStore.initHandle(context.readWorkbookHolder().getFile().getPath());
    }
}
