package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.ExperimentalConsumablesManagementTable;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalConsumablesManagementModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalConsumablesManagementView;

import org.json.JSONObject;

public class ExperimentalConsumablesManagementPresenter extends BasePresenter<ExperimentalConsumablesManagementView> {

    private ExperimentalConsumablesManagementModel experimentalConsumablesManagementModel;

    public ExperimentalConsumablesManagementPresenter() {
        this.experimentalConsumablesManagementModel = new ExperimentalConsumablesManagementModel();
    }

    public void insertData(String id,
                           String experimental_consumables_name,
                           String current_inventory,
                           String maximum_inventory,
                           String minimum_inventory,
                           String model_specification,
                           String unit,
                           String unit_price,
                           String laboratory_room_name) {
        this.experimentalConsumablesManagementModel.insertData(id,
                experimental_consumables_name,
                current_inventory,
                maximum_inventory,
                minimum_inventory,
                model_specification,
                unit,
                unit_price,
                laboratory_room_name, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(ExperimentalConsumablesManagementTable experimentalConsumablesTable) {
        this.experimentalConsumablesManagementModel.insertData(experimentalConsumablesTable.getId(),
                experimentalConsumablesTable.getExperimental_consumables_name(),
                experimentalConsumablesTable.getCurrent_inventory(),
                experimentalConsumablesTable.getMaximum_inventory(),
                experimentalConsumablesTable.getMinimum_inventory(),
                experimentalConsumablesTable.getModel_specification(),
                experimentalConsumablesTable.getUnit(),
                experimentalConsumablesTable.getUnit_price(),
                experimentalConsumablesTable.getLaboratory_room_name(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.experimentalConsumablesManagementModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String teaching_experiment_center_name,
                        String laboratory_name,
                        String experimental_compartment_name,
                        String laboratory_room_name,
                        String model_specification,
                        String experimental_consumables_name) {
        this.experimentalConsumablesManagementModel.getData(teaching_experiment_center_name,
                laboratory_name,
                experimental_compartment_name,
                laboratory_room_name,
                model_specification,
                experimental_consumables_name, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
