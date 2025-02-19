package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class CourseExperimentProjectModel {
    private static final String URL_getCourseExperimentProjectList = "/courseExperimentProject/getData";
    private static final String URL_getQueryConditionList = "/courseExperimentProject/getQueryConditionList";

    public void getData(String college,
                        String course_category,
                        String course_assignment,
                        String enabling_grade,
                        String course,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("college", college)
                .add("course_category", course_category)
                .add("course_assignment", course_assignment)
                .add("enabling_grade", enabling_grade)
                .add("course", course)
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getCourseExperimentProjectList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getQueryConditionList(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getQueryConditionList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
