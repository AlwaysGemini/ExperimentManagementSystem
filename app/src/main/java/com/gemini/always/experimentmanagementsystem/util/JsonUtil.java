package com.gemini.always.experimentmanagementsystem.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.util.JsonUtil.java
 * @Description:
 * @author: 周清
 * @date: 2020-02-07 21:38
 */
public class JsonUtil {

    private static Gson mGson = new Gson();

    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static Object stringToObject(String json, Class classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T object) {
        return mGson.toJson(object);
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 把json 数组转化成list
     *
     * @param jsonArray
     * @param fieldName
     * @return
     */
    public static List<String> jsonArrayToStringList(JSONArray jsonArray, String fieldName) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(jsonObject.getString(fieldName));
            } catch (JSONException e) {
                Logger.e(e, "JSONException");
            }
        }
        return list;
    }

}
