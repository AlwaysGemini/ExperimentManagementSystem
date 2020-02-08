package com.gemini.always.experimentmanagementsystem.util;

import com.gemini.always.experimentmanagementsystem.R;
import com.thl.filechooser.FileChooser;
import com.thl.filechooser.FileInfo;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.util.FileUtils.java
 * @Description:
 * @author: 周清
 * @date: 2020-02-07 21:37
 */
public class FileUtils {
    /**
     * 获取文件格式名
     */
    public static String getFormatName(String fileName) {
        //去掉首尾的空格
        fileName = fileName.trim();
        String s[] = fileName.split("\\.");
        if (s.length >= 2) {
            return s[s.length - 1];
        }
        return "";
    }

    /**
     * 初始化FileChooser
     *
     * @param fileChooser
     */
    public static void initFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("选择导入的表格文件");
        fileChooser.setDoneText("确定");
        fileChooser.setChooseType(FileInfo.FILE_TYPE_FILE);
        fileChooser.showFile(true);
        fileChooser.setThemeColor(R.color.colorAccent);
        fileChooser.open();
    }
}
