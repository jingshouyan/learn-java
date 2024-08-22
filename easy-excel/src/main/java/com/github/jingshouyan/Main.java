package com.github.jingshouyan;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int BATCH_SIZE = 100;
    private static final int MAX_ROWS_PER_SHEET = 1_000_000;



    public static void main(String[] args) {
        // 文件名
        String fileName = "dynamic_multi_sheet_export.xlsx";

        // 创建文件写入器
        try (ExcelWriter excelWriter = EasyExcel.write(new File(fileName), DemoData.class).build()) {
            int sheetNo = 0;
            int currentRow = 0;
            WriteSheet writeSheet = null;
            int max = 20_000_000;
            int total = 0;

            // 模拟从数据源批量获取数据，直到没有更多数据
            while (true) {
                List<DemoData> data = fetchDataFromSource(BATCH_SIZE);
                if (total >= max) {
                    break;
                }

                // 如果当前 Sheet 已达到最大行数，创建新 Sheet
                if (currentRow + data.size() > MAX_ROWS_PER_SHEET) {
                    sheetNo++;
                    currentRow = 0;
                    writeSheet = EasyExcel.writerSheet(sheetNo, "Sheet" + (sheetNo + 1)).build();
                }

                // 初始化第一个 Sheet
                if (writeSheet == null) {
                    writeSheet = EasyExcel.writerSheet(sheetNo, "Sheet" + (sheetNo + 1)).build();
                }

                // 写入当前批次数据
                excelWriter.write(data, writeSheet);

                // 更新当前 Sheet 的行数
                currentRow += data.size();
                total += data.size();

            }
        }

        System.out.println("Export completed: " + fileName);
    }

    // 模拟从数据源批量获取数据的方法
    private static List<DemoData> fetchDataFromSource(int batchSize) {
        // 在实际应用中，这里应该从数据库或其他数据源获取数据
        List<DemoData> data = new ArrayList<>();

        // 模拟条件：假设数据源在某个时刻返回空数据以结束
//        if (Math.random() > 0.999) {
//            return data; // 模拟数据源无数据返回
//        }

        // 模拟填充数据
        for (int i = 0; i < batchSize; i++) {
            DemoData demoData = new DemoData();
            demoData.setName("Name " + Math.random());
            demoData.setValue(Math.random());
            data.add(demoData);
        }

        return data;
    }
}