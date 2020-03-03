package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class LaboratoryCompartmentModel {

    private static final String URL_INSERT_DATA = "/experimentalOrganization/experimentalCompartment/insertData";
    private static final String URL_getQueryConditionList = "/experimentalOrganization/experimentalCompartment/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalOrganization/experimentalCompartment/getData";

    public void insertData(String laboratory_compartment_id,
                           String laboratory_compartment_name,
                           String laboratory_id,
                           String remarks,
                           String enable_flag,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("laboratory_compartment_id", laboratory_compartment_id)
                .add("laboratory_compartment_name", laboratory_compartment_name)
                .add("laboratory_id", laboratory_id)
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

    public void getData(String teaching_experiment_center_name, String laboratory_name, String enable_flag, OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("teaching_experiment_center_name", teaching_experiment_center_name)
                .add("laboratory_name", laboratory_name)
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
