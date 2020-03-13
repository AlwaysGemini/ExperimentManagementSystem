package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class RulesOfSelectingCoursesModel {
    private static final String prefix = "/rulesOfSelectingCourses";
    private static final String URL_INSERT_DATA = prefix + "/insertData";
    private static final String URL_getQueryConditionList = prefix + "/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = prefix + "/getData";

    public void insertData(String year,
                           String semester,
                           String start_time,
                           String end_time,
                           String is_withdrawable,
                           String is_reelect,
                           String is_selective_capacity,
                           String is_click_to_run,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("year", year)
                .add("semester", semester)
                .add("start_time", start_time)
                .add("end_time", end_time)
                .add("is_withdrawable", is_withdrawable)
                .add("is_reelect", is_reelect)
                .add("is_selective_capacity", is_selective_capacity)
                .add("is_click_to_run", is_click_to_run)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_INSERT_DATA, new OkHttpUtils.OnOkHttpUtilsListener() {
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

    public void getData(String year, String semester, OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("year", year)
                .add("semester", semester)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
