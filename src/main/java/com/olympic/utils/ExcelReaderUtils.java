package com.olympic.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.olympic.utils.listener.ExcelTranslationListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel解析器
 *
 * @author eddie.lys
 * @since 2023/4/23
 */
public class ExcelReaderUtils {
    /**
     * 读取excel列数据
     * @param fileName                  文件路径
     * @param readSheetIndexList        解析Sheet
     * @param readIndexList             解析列
     */
    public static void readExcel(String fileName, List<Integer> readSheetIndexList, List<Integer> readIndexList) {
        try (ExcelReader excelReader = EasyExcel.read(fileName).build()){
            List<ReadSheet> sheetReaderList = new ArrayList<>();
            for (Integer readSheetIndex : readSheetIndexList) {
                sheetReaderList.add(EasyExcel.readSheet(readSheetIndex)
                        .autoTrim(true)
                        .registerReadListener(new ExcelTranslationListener(readIndexList))
                        .build());
            }
            excelReader.read(sheetReaderList);
        }
    }
}
