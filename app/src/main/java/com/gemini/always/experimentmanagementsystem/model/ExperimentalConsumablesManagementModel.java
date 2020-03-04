package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentalConsumablesManagementModel {

    private static final String URL_INSERT_DATA = "/experimentalConsumables/insertData";
    private static final String URL_getQueryConditionList = "/experimentalConsumables/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalConsumables/getData";

    public void insertData(String experimental_consumables_id,
                           String experimental_consumables_name,
                           String current_inventory,
                           String maximum_inventory,
                           String minimum_inventory,
                           String model_specification,
                           String unit,
                           String unit_price,
                           String laboratory_room_id,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_consumables_id", experimental_consumables_id)
                .add("experimental_consumables_name", experimental_consumables_name)
                .add("current_inventory", current_inventory)
                .add("maximum_inventory", maximum_inventory)
                .add("minimum_inventory", minimum_inventory)
                .add("model_specification", model_specification)
                .add("unit", unit)
                .add("unit_price", unit_price)
                .add("laboratory_room_id", laboratory_room_id)
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
                        String laboratory_room_name,
                        String model_specification,
                        String experimental_consumables_name,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("teaching_experiment_center_name", teaching_experiment_center_name)
                .add("laboratory_name", laboratory_name)
                .add("laboratory_compartment_name", laboratory_compartment_name)
                .add("laboratory_room_name", laboratory_room_name)
                .add("model_specification", model_specification)
                .add("experimental_consumables_name", experimental_consumables_name)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
