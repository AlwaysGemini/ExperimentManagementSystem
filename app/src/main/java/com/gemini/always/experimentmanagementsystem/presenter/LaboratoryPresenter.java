package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.LaboratoryTable;
import com.gemini.always.experimentmanagementsystem.model.LaboratoryModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryView;

import org.json.JSONObject;

public class LaboratoryPresenter extends BasePresenter<LaboratoryView> {

    private LaboratoryModel laboratoryModel;

    public LaboratoryPresenter() {
        this.laboratoryModel = new LaboratoryModel();
    }

    public void insertData(String laboratory_code,
                           String laboratory_name,
                           String affiliated_teaching_experiment_center,
                           String laboratory_director,
                           String rules_and_regulations,
                           String remarks,
                           String enable_flag) {
        this.laboratoryModel.insertData(laboratory_code,
                laboratory_name,
                affiliated_teaching_experiment_center,
                laboratory_director,
                rules_and_regulations,
                remarks,
                enable_flag, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(LaboratoryTable laboratoryTable) {
        this.laboratoryModel.insertData(laboratoryTable.getLaboratory_id(),
                laboratoryTable.getLaboratory_name(),
                laboratoryTable.getTeaching_experiment_center_name(),
                laboratoryTable.getLaboratory_director(),
                laboratoryTable.getRules_and_regulations(),
                laboratoryTable.getRemarks(),
                laboratoryTable.getEnable_flag(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.laboratoryModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String affiliated_teaching_experiment_center, String laboratory_name, String enable_flag) {
        this.laboratoryModel.getData(affiliated_teaching_experiment_center, laboratory_name, enable_flag, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
