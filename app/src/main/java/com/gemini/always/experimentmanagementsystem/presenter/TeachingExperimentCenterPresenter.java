package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.TeachingExperimentCenterTable;
import com.gemini.always.experimentmanagementsystem.model.TeachingExperimentCenterModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.TeachingExperimentCenterView;

import org.json.JSONObject;

public class TeachingExperimentCenterPresenter extends BasePresenter<TeachingExperimentCenterView> {

    private TeachingExperimentCenterModel teachingExperimentCenterModel;

    public TeachingExperimentCenterPresenter() {
        this.teachingExperimentCenterModel = new TeachingExperimentCenterModel();
    }

    public void insertData(String teaching_experiment_center_code,
                           String teaching_experiment_center_name,
                           String laboratory_type,
                           String subordinate_unit,
                           String subordinate_discipline,
                           String year_of_establishment,
                           String remarks,
                           String enable_flag) {
        this.teachingExperimentCenterModel.insertData(teaching_experiment_center_code,
                teaching_experiment_center_name,
                laboratory_type,
                subordinate_unit,
                subordinate_discipline,
                year_of_establishment,
                remarks,
                enable_flag, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(TeachingExperimentCenterTable teachingExperimentCenterTable) {
        this.teachingExperimentCenterModel.insertData(teachingExperimentCenterTable.getTeaching_experiment_center_id(),
                teachingExperimentCenterTable.getTeaching_experiment_center_name(),
                teachingExperimentCenterTable.getLaboratory_type(),
                teachingExperimentCenterTable.getSubordinate_unit(),
                teachingExperimentCenterTable.getSubordinate_discipline(),
                teachingExperimentCenterTable.getYear_of_establishment(),
                teachingExperimentCenterTable.getRemarks(),
                teachingExperimentCenterTable.getEnable_flag(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.teachingExperimentCenterModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String laboratory_type, String enable_flag) {
        this.teachingExperimentCenterModel.getData(laboratory_type, enable_flag, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
