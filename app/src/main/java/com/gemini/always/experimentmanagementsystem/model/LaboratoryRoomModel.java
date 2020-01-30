package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class LaboratoryRoomModel {

    private static final String URL_INSERT_DATA = "/experimentalOrganization/laboratoryRoom/insertData";
    private static final String URL_getQueryConditionList = "/experimentalOrganization/laboratoryRoom/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalOrganization/laboratoryRoom/getData";

    public void insertData(String laboratory_room_code,
                           String laboratory_room_name,
                           String affiliated_experimental_compartment,
                           String nature_of_experimental_site,
                           String category_of_scientific_research_base,
                           String person_in_charge_of_the_experimental_room,
                           String status_of_joint_construction,
                           String campus,
                           String capacity,
                           String remarks,
                           String enable_flag,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("laboratory_room_code", laboratory_room_code)
                .add("laboratory_room_name", laboratory_room_name)
                .add("affiliated_experimental_compartment", affiliated_experimental_compartment)
                .add("nature_of_experimental_site", nature_of_experimental_site)
                .add("category_of_scientific_research_base", category_of_scientific_research_base)
                .add("person_in_charge_of_the_experimental_room", person_in_charge_of_the_experimental_room)
                .add("status_of_joint_construction", status_of_joint_construction)
                .add("campus", campus)
                .add("capacity", capacity)
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

    public void getData(String affiliated_teaching_experiment_center,
                        String affiliated_laboratory,
                        String affiliated_experimental_compartment,
                        String nature_of_experimental_site,
                        String category_of_scientific_research_base,
                        String status_of_joint_construction,
                        String campus,
                        String enable_flag,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("affiliated_teaching_experiment_center", affiliated_teaching_experiment_center)
                .add("affiliated_laboratory", affiliated_laboratory)
                .add("affiliated_experimental_compartment", affiliated_experimental_compartment)
                .add("nature_of_experimental_site", nature_of_experimental_site)
                .add("category_of_scientific_research_base", category_of_scientific_research_base)
                .add("status_of_joint_construction", status_of_joint_construction)
                .add("campus", campus)
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
