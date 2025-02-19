package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentalEquipmentTable;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalEquipmentModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalEquipmentView;

import org.json.JSONObject;

public class ExperimentalEquipmentPresenter extends BasePresenter<ExperimentalEquipmentView> {

    private ExperimentalEquipmentModel experimentalEquipmentModel;

    public ExperimentalEquipmentPresenter() {
        this.experimentalEquipmentModel = new ExperimentalEquipmentModel();
    }

    public void insertData(String experimental_equipment_id,
                           String experimental_equipment_name,
                           String value,
                           String laboratory_room_id,
                           String is_movable,
                           String procurement_time) {
        this.experimentalEquipmentModel.insertData(experimental_equipment_id,
                experimental_equipment_name,
                value,
                laboratory_room_id,
                is_movable,
                procurement_time, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(ExperimentalEquipmentTable experimentalEquipmentTable) {
        this.experimentalEquipmentModel.insertData(experimentalEquipmentTable.getExperimental_equipment_id(),
                experimentalEquipmentTable.getExperimental_equipment_name(),
                experimentalEquipmentTable.getValue(),
                experimentalEquipmentTable.getLaboratory_room_name(),
                experimentalEquipmentTable.getIs_movable(),
                experimentalEquipmentTable.getProcurement_time(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.experimentalEquipmentModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String teaching_experiment_center_name,
                        String laboratory_name,
                        String laboratory_compartment_name,
                        String laboratory_room_name,
                        String is_movable,
                        String experimental_equipment_name) {
        this.experimentalEquipmentModel.getData(teaching_experiment_center_name,
                laboratory_name,
                laboratory_compartment_name,
                laboratory_room_name,
                is_movable,
                experimental_equipment_name, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
