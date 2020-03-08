package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentSchedulingModel {

    private static final String prefix = "/experimentScheduling";
    private static final String URL_INSERT_DATA = prefix + "/insertData";
    private static final String URL_getFreeTimeData = prefix + "/getFreeTimeData";
    private static final String URL_getUnAllocationData = prefix + "/getUnAllocationData";

    public void getFreeTimeData(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getFreeTimeData, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getUnAllocationData(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("teacher_id", "2")
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getUnAllocationData, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void insertData(String experiment_scheduling_id,
                           String instructor_id,
                           String laboratory_room_id,
                           String week,
                           String day,
                           String start_time,
                           String length,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_scheduling_id", experiment_scheduling_id)
                .add("instructor_id", instructor_id)
                .add("laboratory_room_id", laboratory_room_id)
                .add("week", week)
                .add("day", day)
                .add("start_time", start_time)
                .add("length", length)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_INSERT_DATA, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
