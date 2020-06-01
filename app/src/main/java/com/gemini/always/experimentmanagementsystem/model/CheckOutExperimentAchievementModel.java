package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class CheckOutExperimentAchievementModel {
    private static String TAG = "CheckOutExperimentAchievementModel";

    private static final String URL_checkOutExperimentAchievement = "/experimentAchievement/checkOutAchievement";

    public void checkOutAchievement(String student_id,OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener){
        FormBody formBody = new FormBody
                .Builder()
                .add("student_id",student_id)
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_checkOutExperimentAchievement, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
