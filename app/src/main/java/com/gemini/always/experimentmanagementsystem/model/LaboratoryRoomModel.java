package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class LaboratoryRoomModel {

    private static final String URL_INSERT_DATA = "/experimentalOrganization/laboratoryRoom/insertData";
    private static final String URL_getQueryConditionList = "/experimentalOrganization/laboratoryRoom/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalOrganization/laboratoryRoom/getData";

    public void insertData(String laboratory_room_id,
                           String laboratory_room_name,
                           String laboratory_compartment_id,
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
                .add("laboratory_room_id", laboratory_room_id)
                .add("laboratory_room_name", laboratory_room_name)
                .add("laboratory_compartment_id", laboratory_compartment_id)
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

    public void getData(String teaching_experiment_center_name,
                        String laboratory_name,
                        String laboratory_compartment_name,
                        String nature_of_experimental_site,
                        String category_of_scientific_research_base,
                        String status_of_joint_construction,
                        String campus,
                        String enable_flag,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("teaching_experiment_center_name", teaching_experiment_center_name)
                .add("laboratory_name", laboratory_name)
                .add("laboratory_compartment_name", laboratory_compartment_name)
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
