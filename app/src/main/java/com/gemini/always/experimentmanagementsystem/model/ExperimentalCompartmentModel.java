package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentalCompartmentModel {

    private static final String URL_INSERT_DATA = "/experimentalOrganization/experimentalCompartment/insertData";
    private static final String URL_getQueryConditionList = "/experimentalOrganization/experimentalCompartment/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalOrganization/experimentalCompartment/getData";

    public void insertData(String experimental_compartment_code,
                           String experimental_compartment_name,
                           String affiliated_teaching_experiment_center,
                           String affiliated_laboratory,
                           String remarks,
                           String enable_flag,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_compartment_code", experimental_compartment_code)
                .add("experimental_compartment_name", experimental_compartment_name)
                .add("affiliated_teaching_experiment_center", affiliated_teaching_experiment_center)
                .add("affiliated_laboratory", affiliated_laboratory)
                .add("remarks", remarks)
                .add("enable_flag", enable_flag)
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

    public void getData(String affiliated_teaching_experiment_center, String affiliated_laboratory, String enable_flag, OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("affiliated_teaching_experiment_center", affiliated_teaching_experiment_center)
                .add("affiliated_laboratory", affiliated_laboratory)
                .add("enable_flag", enable_flag)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
