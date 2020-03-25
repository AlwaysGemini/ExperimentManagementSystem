package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentalCourseSelectionModel {
    private static final String prefix = "/experimentalCourseSelection";
    private static final String URL_getExperimentalCourseList = prefix + "/getExperimentalCourseList";
    private static final String URL_select = prefix + "/select";

    public void getExperimentalCourseList(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getExperimentalCourseList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void select(String student_id,
                       String experimental_teaching_class_id,
                       OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("student_id", student_id)
                .add("experimental_teaching_class_id", experimental_teaching_class_id)
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_select, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
