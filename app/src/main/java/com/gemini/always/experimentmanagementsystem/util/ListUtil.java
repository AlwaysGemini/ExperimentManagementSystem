package com.gemini.always.experimentmanagementsystem.util;

import org.json.JSONArray;

import java.util.List;

public class ListUtil {
    public static void addAllDataIntoList(JSONArray jsonArray ,String fieldName,List<String> list){
        list.clear();
        list.add("全部");
        list.addAll(JsonUtil.jsonArrayToStringList(jsonArray, fieldName));
    }
}
