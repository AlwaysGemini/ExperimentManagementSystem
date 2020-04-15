package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

public class ExperimentAchievementEntryModel {
    private static final String prefix = "/experimentAchievement";
    private static final String URL_getExperimentAchievementTableSummary = prefix + "/getExperimentAchievementTableSummary";
    private static final String URL_getTemplate = prefix + "/getTemplate";
    private static final String URL_upload = prefix + "/upload";

    public void getExperimentAchievementTableSummary(String user_id,
                                                     OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("user_id", user_id)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getExperimentAchievementTableSummary, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getTemplate(String experiment_course_match_id,
                            OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_course_match_id", experiment_course_match_id)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTemplate, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void importExperimentAchievement(String filePath,
                                            String fileName,
                                            String experiment_course_match_id,
                                            OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        Map<String, String> map = new HashMap<>();
        map.put("experiment_course_match_id", experiment_course_match_id);
        OkHttpUtils.upload(URL_upload, new File(filePath), fileName, map, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
