package com.lbc.mo.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lbc.mo.constants.MonitorConstants;
import com.lbc.mo.entity.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

public class ExcelUtil {

    private ExcelUtil() {
    }

    //data to excel
    public static String createExcel(List<User> users) {
        String path = MonitorConstants.COINS_EXCEL_OUTPUT_PATH;
        File file = new File(System.getProperty("user.dir") + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        OutputStream out = null;
        String filePath = file.getPath() + String.format(MonitorConstants.COINS_EXCEL_OUTPUT_PATH_FILENAME, LocalDate.now());

        try {
            out = new FileOutputStream(filePath);

            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0, User.class);
            writer.write(users, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
}
