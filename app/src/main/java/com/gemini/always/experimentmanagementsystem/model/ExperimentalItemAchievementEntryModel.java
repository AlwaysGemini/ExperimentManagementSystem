package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

public class ExperimentalItemAchievementEntryModel {
    private static final String prefix = "/experimentalItemAchievement";
    private static final String URL_getExperimentItemAchievementTableSummary = prefix + "/getExperimentItemAchievementTableSummary";
    private static final String URL_getTemplate = prefix + "/getTemplate";
    private static final String URL_uploadExperimentalItemAchievement = prefix + "/uploadExperimentalItemAchievement";

    public void getExperimentItemAchievementTableSummary(String user_id,
                                                         OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("user_id", user_id)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getExperimentItemAchievementTableSummary, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getTemplate(String experiment_course_match_id,
                            String experiment_item_id,
                            OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_course_match_id", experiment_course_match_id)
                .add("experiment_item_id", experiment_item_id)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTemplate, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void importExperimentalItemAchievement(String filePath,
                                                  String fileName,
                                                  String experiment_item_id,
                                                  String experiment_course_match_id,
                                                  OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        Map<String, String> map = new HashMap<>();
        map.put("experiment_item_id", experiment_item_id);
        map.put("experiment_course_match_id", experiment_course_match_id);
        OkHttpUtils.upload(URL_uploadExperimentalItemAchievement, new File(filePath), fileName, map, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
