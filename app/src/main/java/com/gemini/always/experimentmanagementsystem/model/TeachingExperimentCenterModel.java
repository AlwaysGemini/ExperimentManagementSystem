package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class TeachingExperimentCenterModel {

    private static final String URL_INSERT_DATA = "/experimentalOrganization/teachingExperimentCenter/insertData";
    private static final String URL_getQueryConditionList = "/experimentalOrganization/teachingExperimentCenter/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalOrganization/teachingExperimentCenter/getData";

    public void insertData(String code_of_teaching_experiment_center,
                           String name_of_teaching_experiment_center,
                           String laboratory_type,
                           String subordinate_unit,
                           String subordinate_discipline,
                           String year_of_establishment,
                           String remarks,
                           String enable_flag,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("code_of_teaching_experiment_center", code_of_teaching_experiment_center)
                .add("name_of_teaching_experiment_center", name_of_teaching_experiment_center)
                .add("laboratory_type", laboratory_type)
                .add("subordinate_unit", subordinate_unit)
                .add("subordinate_discipline", subordinate_discipline)
                .add("year_of_establishment", year_of_establishment)
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

    public void getData(String laboratory_type, String enable_flag, OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("laboratory_type", laboratory_type)
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
