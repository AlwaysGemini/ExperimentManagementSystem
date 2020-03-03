package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.LaboratoryCompartmentTable;
import com.gemini.always.experimentmanagementsystem.model.LaboratoryCompartmentModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalCompartmentView;

import org.json.JSONObject;

public class ExperimentalCompartmentPresenter extends BasePresenter<ExperimentalCompartmentView> {

    private LaboratoryCompartmentModel laboratoryCompartmentModel;

    public ExperimentalCompartmentPresenter() {
        this.laboratoryCompartmentModel = new LaboratoryCompartmentModel();
    }

    public void insertData(String experimental_compartment_code,
                           String experimental_compartment_name,
                           String affiliated_laboratory,
                           String remarks,
                           String enable_flag) {
        this.laboratoryCompartmentModel.insertData(experimental_compartment_code,
                experimental_compartment_name,
                affiliated_laboratory,
                remarks,
                enable_flag, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(LaboratoryCompartmentTable laboratoryCompartmentTable) {
        this.laboratoryCompartmentModel.insertData(laboratoryCompartmentTable.getLaboratory_compartment_id(),
                laboratoryCompartmentTable.getLaboratory_compartment_name(),
                laboratoryCompartmentTable.getLaboratory_name(),
                laboratoryCompartmentTable.getRemarks(),
                laboratoryCompartmentTable.getEnable_flag(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.laboratoryCompartmentModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String affiliated_teaching_experiment_center, String affiliated_laboratory, String enable_flag) {
        this.laboratoryCompartmentModel.getData(affiliated_teaching_experiment_center, affiliated_laboratory, enable_flag, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
