package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentAchievementSummaryModel {
    private static final String prefix = "/experimentalAchievement";
    private static final String URL_getExperimentalAchievementSummary = prefix + "/getExperimentalAchievementSummary";

    public void getExperimentalAchievementSummary(String user_id,
                                                  OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("user_id", user_id)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getExperimentalAchievementSummary, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
