package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class MaintenanceOfTeachingExperimentalClassModel {

    private static final String URL_INSERT_DATA = "/maintenanceOfTeachingExperimentalClass/insertData";
    private static final String URL_getQueryConditionList = "/maintenanceOfTeachingExperimentalClass/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/maintenanceOfTeachingExperimentalClass/getData";

    public void insertData(String experimental_teaching_class_id,
                           String experimental_teaching_class_name,
                           String year,
                           String semester,
                           String composition_of_teaching_class,
                           String course_experiment_outline_id,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_teaching_class_id", experimental_teaching_class_id)
                .add("experimental_teaching_class_name", experimental_teaching_class_name)
                .add("year", year)
                .add("semester", semester)
                .add("composition_of_teaching_class", composition_of_teaching_class)
                .add("course_experiment_outline_id", course_experiment_outline_id)
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

    public void getData(String year,
                        String semester,
                        String college,
                        String experimental_teaching_class_name,
                        String course,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("year", year)
                .add("semester", semester)
                .add("college", college)
                .add("experimental_teaching_class_name", experimental_teaching_class_name)
                .add("course", course)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
