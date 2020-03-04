package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentItemTable;
import com.gemini.always.experimentmanagementsystem.model.ExperimentItemManagementModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalProjectManagementView;

import org.json.JSONObject;

public class ExperimentItemManagementPresenter extends BasePresenter<ExperimentalProjectManagementView> {

    private ExperimentItemManagementModel experimentItemManagementModel;

    public ExperimentItemManagementPresenter() {
        this.experimentItemManagementModel = new ExperimentItemManagementModel();
    }

    public void insertData(String experiment_item_id,
                           String experiment_item_name,
                           String experiment_content,
                           String experiment_hours,
                           String experiment_credits,
                           String experiment_attribute,
                           String experiment_type,
                           String experiment_category,
                           String subordinate_unit,
                           String subordinate_discipline,
                           String experiment_requirements) {
        this.experimentItemManagementModel.insertData(experiment_item_id,
                experiment_item_name,
                experiment_content,
                experiment_hours,
                experiment_credits,
                experiment_attribute,
                experiment_type,
                experiment_category,
                subordinate_unit,
                subordinate_discipline,
                experiment_requirements,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(ExperimentItemTable experimentItemTable) {
        this.experimentItemManagementModel.insertData(experimentItemTable.getExperiment_item_id(),
                experimentItemTable.getExperiment_item_name(),
                experimentItemTable.getExperiment_content(),
                experimentItemTable.getExperiment_hours(),
                experimentItemTable.getExperiment_credits(),
                experimentItemTable.getExperiment_attribute(),
                experimentItemTable.getExperiment_type(),
                experimentItemTable.getExperiment_category(),
                experimentItemTable.getSubordinate_unit(),
                experimentItemTable.getExperiment_requirements(),
                experimentItemTable.getExperiment_requirements(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.experimentItemManagementModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String experimental_properties,
                        String experimental_type,
                        String experimental_category,
                        String experimental_project_name) {
        this.experimentItemManagementModel.getData(experimental_properties, experimental_type, experimental_category, experimental_project_name, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
