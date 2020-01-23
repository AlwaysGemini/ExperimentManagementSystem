package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class LaboratoryModel {

    private static final String URL_INSERT_DATA = "/experimentalOrganization/laboratory/insertData";
    private static final String URL_getQueryConditionList = "/experimentalOrganization/laboratory/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalOrganization/laboratory/getData";

    public void insertData(String laboratory_code,
                           String laboratory_name,
                           String affiliated_teaching_experiment_center,
                           String laboratory_director,
                           String rules_and_regulations,
                           String remarks,
                           String enable_flag,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener){
        FormBody formBody = new FormBody
                .Builder()
                .add("laboratory_code",laboratory_code)
                .add("laboratory_name",laboratory_name)
                .add("affiliated_teaching_experiment_center",affiliated_teaching_experiment_center)
                .add("laboratory_director",laboratory_director)
                .add("rules_and_regulations",rules_and_regulations)
                .add("remarks",remarks)
                .add("enable_flag",enable_flag)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_INSERT_DATA, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getQueryConditionList(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener){
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

    public void getData(String affiliated_teaching_experiment_center,String laboratory_name,String enable_flag,OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener){
        FormBody formBody = new FormBody
                .Builder()
                .add("affiliated_teaching_experiment_center",affiliated_teaching_experiment_center)
                .add("laboratory_name",laboratory_name)
                .add("enable_flag",enable_flag)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
