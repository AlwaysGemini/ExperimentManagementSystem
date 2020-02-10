package com.gemini.always.experimentmanagementsystem.util;

import android.content.Context;

import com.gemini.always.experimentmanagementsystem.custom.customTableView.TableColumn;
import com.orhanobut.logger.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.gemini.always.experimentmanagementsystem.util.ReflectUtil.invokeGet;
import static com.gemini.always.experimentmanagementsystem.util.ReflectUtil.invokeSet;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.util.ExcelUtils.java
 * @Description: 表格工具类
 * @author: 周清
 * @date: 2020-02-07 21:11
 */
public class ExcelUtils {

    static private Workbook wb;
    static private Sheet sheet;
    static private Row row;
    static private Cell cell;

    /**
     * 创建表格
     *
     * @param context  上下文
     * @param fileName 指定所创建的文件名
     * @param list     表格数据
     * @param clazz    给定表格的类，进而通过反射获得类的get方法获取数据
     */
    public static <T> void createExcel(Context context, String fileName, List<T> list, Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("sheet"));

        row = sheet.createRow(0);
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
            if (fieldAnnotation != null) {
                TableColumn smartColumn = (TableColumn) fieldAnnotation;
                cell = row.createCell(smartColumn.id() - 1);
                cell.setCellValue(smartColumn.name());
            }
        }
        for (int i = 1; i <= list.size(); i++) {
            row = sheet.createRow(i);
            for (Field field : fields) {
                field.setAccessible(true);
                Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
                if (fieldAnnotation != null) {
                    TableColumn smartColumn = (TableColumn) fieldAnnotation;
                    cell = row.createCell(smartColumn.id() - 1);
                    cell.setCellValue((String) invokeGet(list.get(i - 1), field.getName()));
                }
            }
        }

        try {
            File outFile = new File(context.getExternalFilesDir(null), fileName + ".xlsx");
            OutputStream outputStream = new FileOutputStream(outFile.getAbsolutePath());
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            Logger.e(e, "IOException:");
        }
    }

    /**
     * 创建表格
     *
     * @param fileName 指定所创建的文件名
     * @param clazz    给定表格的类，进而通过反射获得类的set方法
     * @param <T>      泛型
     * @return
     */
    public static <T> List<T> readExcelContent(String fileName, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            InputStream is = new FileInputStream(fileName);
            String postfix = fileName.substring(fileName.lastIndexOf("."),
                    fileName.length());
            if (postfix.equals(".xls")) {
                // 针对 2003 Excel 文件
                wb = new HSSFWorkbook(new POIFSFileSystem(is));
                sheet = wb.getSheetAt(0);
            } else {
                // 针对2007 Excel 文件
                wb = new XSSFWorkbook(is);
                sheet = wb.getSheetAt(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();// 得到总行数
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();

        T temp = null;
        try {
            temp = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            Logger.e(e, "InstantiationException and IllegalAccessException:");
        }
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            try {
                temp = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                Logger.e(e, "InstantiationException and IllegalAccessException:");
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Annotation fieldAnnotation = field.getAnnotation(TableColumn.class);
                if (fieldAnnotation != null) {
                    TableColumn smartColumn = (TableColumn) fieldAnnotation;
                    cell = row.getCell(smartColumn.id() - 1);
                    invokeSet(temp, field.getName(), cell.getStringCellValue());
                }
            }
            list.add(temp);
        }
        return list;
    }
}
