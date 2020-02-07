package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class MaintenanceOfTeachingExperimentalClassModel {

    private static final String URL_INSERT_DATA = "/maintenanceOfTeachingExperimentalClass/insertData";
    private static final String URL_getQueryConditionList = "/maintenanceOfTeachingExperimentalClass/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/maintenanceOfTeachingExperimentalClass/getData";

    public void insertData(String school_year,
                           String semester,
                           String name_of_teaching_class,
                           String composition_of_teaching_class,
                           String school_of_commencement,
                           String course_name,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("school_year", school_year)
                .add("semester", semester)
                .add("name_of_teaching_class", name_of_teaching_class)
                .add("composition_of_teaching_class", composition_of_teaching_class)
                .add("school_of_commencement", school_of_commencement)
                .add("course_name", course_name)
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

    public void getData(String school_year,
                        String semester,
                        String school_of_commencement,
                        String name_of_teaching_class,
                        String course,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("school_year", school_year)
                .add("semester", semester)
                .add("school_of_commencement", school_of_commencement)
                .add("name_of_teaching_class", name_of_teaching_class)
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
