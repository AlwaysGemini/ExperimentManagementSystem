package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentAchievementExaminingModel {
    private static final String prefix = "/experimentAchievement";
    private static final String URL_getExperimentAchievementTableSummary = prefix + "/getExperimentAchievementTableSummary";
    private static final String URL_examining = prefix + "/examining";

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

    public void examining(String experiment_course_match_id,
                          String experiment_achievement_table_state,
                          OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_course_match_id", experiment_course_match_id)
                .add("experiment_achievement_table_state", experiment_achievement_table_state)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_examining, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
