package com.gemini.always.experimentmanagementsystem.util;

import android.content.Context;

import com.bin.david.form.annotation.ColumnType;
import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.data.column.Column;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExcelUtils {

    static private Workbook wb;
    static private Sheet sheet;
    static private Row row;
    static private Cell cell;

    public static <T> void createExcel(Context context,String fileName, List<T> list, Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("sheet"));

        row = sheet.createRow(0);
        for (Field field:fields){
            field.setAccessible(true);
            Annotation fieldAnnotation = field.getAnnotation(SmartColumn.class);
            if(fieldAnnotation != null){
                SmartColumn smartColumn = (SmartColumn) fieldAnnotation;
                cell = row.createCell(smartColumn.id()-1);
                cell.setCellValue(smartColumn.name());
            }
        }
        for (int i = 1; i <= list.size() ;i++){
            row = sheet.createRow(i);
            for (Field field:fields){
                field.setAccessible(true);
                Annotation fieldAnnotation = field.getAnnotation(SmartColumn.class);
                if(fieldAnnotation != null){
                    SmartColumn smartColumn = (SmartColumn) fieldAnnotation;
                    cell = row.createCell(smartColumn.id()-1);
                    try {
                        cell.setCellValue((String) Objects.requireNonNull(getGetMethod(clazz, field.getName())).invoke(list.get(i-1)));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        Logger.e(e, "IllegalAccessException and InvocationTargetException:");
                    }
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
        for (int i = 1; i <= rowNum ;i++){
            row = sheet.getRow(i);
            try {
                temp = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                Logger.e(e, "InstantiationException and IllegalAccessException:");
            }
            for (Field field:fields){
                field.setAccessible(true);
                Annotation fieldAnnotation = field.getAnnotation(SmartColumn.class);
                if(fieldAnnotation != null){
                    SmartColumn smartColumn = (SmartColumn) fieldAnnotation;
                    cell = row.getCell(smartColumn.id()-1);
                    try {
                        getSetMethod(clazz,field.getName()).invoke(temp,(String)cell.getStringCellValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        Logger.e(e, "IllegalAccessException and InvocationTargetException:");
                    }
                }
            }
            list.add(temp);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            return method;
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
        return null;
    }

    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
    }

    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            Logger.e(e, "Exception:");
        }
        return null;
    }
}
