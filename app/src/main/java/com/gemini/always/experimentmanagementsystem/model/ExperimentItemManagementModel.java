package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentItemManagementModel {
    private static final String URL_INSERT_DATA = "/experimentalProject/insertData";
    private static final String URL_getQueryConditionList = "/experimentalProject/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalProject/getData";

    public void insertData(String experiment_item_id,
                           String experiment_item_name,
                           String experiment_content,
                           String experiment_hours,
                           String experiment_credits,
                           String experiment_attribute,
                           String experiment_type,
                           String experiment_category,
                           String subordinate_unit,
                           String subordinate_discipline,
                           String experiment_requirements,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_item_id", experiment_item_id)
                .add("experiment_item_name", experiment_item_name)
                .add("experiment_content", experiment_content)
                .add("experiment_hours", experiment_hours)
                .add("experiment_credits", experiment_credits)
                .add("experiment_attribute", experiment_attribute)
                .add("experiment_type", experiment_type)
                .add("experiment_category", experiment_category)
                .add("subordinate_unit", subordinate_unit)
                .add("subordinate_discipline", subordinate_discipline)
                .add("experiment_requirements", experiment_requirements)
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

    public void getData(String experiment_attribute,
                        String experiment_type,
                        String experiment_category,
                        String experiment_project_name,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_attribute", experiment_attribute)
                .add("experiment_type", experiment_type)
                .add("experiment_category", experiment_category)
                .add("experiment_project_name", experiment_project_name)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
