package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class UnmatchedOfExperimentCourseModel {
    private static final String prefix = "/experimentCourseMatch";
    private static final String URL_getUnmatchedList = prefix + "/getUnmatchedList";
    private static final String URL_match = prefix + "/match";

    public void getUnmatchedList(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getUnmatchedList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void match(String experimental_teaching_class_id,
                      String capacity,
                      OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_teaching_class_id", experimental_teaching_class_id)
                .add("capacity", capacity)
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_match, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
