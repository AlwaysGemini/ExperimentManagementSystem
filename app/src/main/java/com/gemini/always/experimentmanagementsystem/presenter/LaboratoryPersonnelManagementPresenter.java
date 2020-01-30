package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.LaboratoryPersonnelManagementModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.LaboratoryPersonnelManagementView;

import org.json.JSONObject;

public class LaboratoryPersonnelManagementPresenter extends BasePresenter<LaboratoryPersonnelManagementView> {

    private LaboratoryPersonnelManagementModel laboratoryPersonnelManagementModel;

    public LaboratoryPersonnelManagementPresenter(){
        this.laboratoryPersonnelManagementModel = new LaboratoryPersonnelManagementModel();
    }

    public void insertData(String job_number,
                           String full_name,
                           String sex,
                           String title,
                           String laboratory_name,
                           String incumbency) {
        this.laboratoryPersonnelManagementModel.insertData(job_number,
                full_name,
                sex,
                title,
                laboratory_name,
                incumbency, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.laboratoryPersonnelManagementModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String teaching_experiment_center_name, String laboratory_name, String incumbency ,String full_name) {
        this.laboratoryPersonnelManagementModel.getData(teaching_experiment_center_name, laboratory_name, incumbency, full_name,new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
