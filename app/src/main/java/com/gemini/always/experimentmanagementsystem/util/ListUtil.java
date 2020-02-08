package com.gemini.always.experimentmanagementsystem.util;

import org.json.JSONArray;

import java.util.List;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.util.ListUtil.java
 * @Description:
 * @author: 周清
 * @date: 2020-02-07 21:38
 */
public class ListUtil {
    public static void addAllDataIntoList(JSONArray jsonArray, String fieldName, List<String> list) {
        list.clear();
        list.add("全部");
        list.addAll(JsonUtil.jsonArrayToStringList(jsonArray, fieldName));
    }
}
