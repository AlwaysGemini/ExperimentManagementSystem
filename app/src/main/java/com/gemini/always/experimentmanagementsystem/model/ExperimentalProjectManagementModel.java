package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentalProjectManagementModel {
    private static final String URL_INSERT_DATA = "/experimentalProject/insertData";
    private static final String URL_getQueryConditionList = "/experimentalProject/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalProject/getData";

    public void insertData(String experimental_project_code,
                           String experimental_project_name,
                           String experimental_content,
                           String experimental_hours,
                           String experimental_credits,
                           String experimental_properties,
                           String experimental_type,
                           String experimental_category,
                           String affiliation,
                           String subject,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_project_code", experimental_project_code)
                .add("experimental_project_name", experimental_project_name)
                .add("experimental_content", experimental_content)
                .add("experimental_hours", experimental_hours)
                .add("experimental_credits", experimental_credits)
                .add("experimental_properties", experimental_properties)
                .add("experimental_type", experimental_type)
                .add("experimental_category", experimental_category)
                .add("affiliation", affiliation)
                .add("subject", subject)
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

    public void getData(String experimental_properties,
                        String experimental_type,
                        String experimental_category,
                        String experimental_project_name,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_properties", experimental_properties)
                .add("experimental_type", experimental_type)
                .add("experimental_category", experimental_category)
                .add("experimental_project_name", experimental_project_name)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
