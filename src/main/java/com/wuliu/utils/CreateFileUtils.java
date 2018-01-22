package com.wuliu.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 根据日期生成文件
 * @author joy
 * @date 2018年1月2日
 */

public class CreateFileUtils {
    private String filePath;
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 创建文件夹，文件夹以日期命名
     * @param path
     */
    public static String createDateFile(String path) {
        Calendar date=Calendar.getInstance();   
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd-HHmmss");
        String name=format.format(date.getTime());
        // 格式化并转换为String类型
        String filePath =path+"\\"+name;
        File file=new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return filePath;
    }

}
