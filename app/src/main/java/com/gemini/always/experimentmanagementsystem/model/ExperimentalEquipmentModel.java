package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentalEquipmentModel {
    private static final String URL_INSERT_DATA = "/experimentalEquipmentManagement/insertData";
    private static final String URL_getQueryConditionList = "/experimentalEquipmentManagement/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalEquipmentManagement/getData";

    public void insertData(String id,
                           String experimental_equipment_name,
                           String value,
                           String laboratory_room_name,
                           String is_movable,
                           String procurement_time,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("id", id)
                .add("experimental_equipment_name", experimental_equipment_name)
                .add("value", value)
                .add("laboratory_room_name", laboratory_room_name)
                .add("is_movable", is_movable)
                .add("procurement_time", procurement_time)
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
                        String experimental_compartment_name,
                        String laboratory_room_name,
                        String is_movable,
                        String experimental_equipment_name,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("teaching_experiment_center_name", teaching_experiment_center_name)
                .add("laboratory_name", laboratory_name)
                .add("experimental_compartment_name", experimental_compartment_name)
                .add("laboratory_room_name", laboratory_room_name)
                .add("is_movable", is_movable)
                .add("experimental_equipment_name", experimental_equipment_name)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
