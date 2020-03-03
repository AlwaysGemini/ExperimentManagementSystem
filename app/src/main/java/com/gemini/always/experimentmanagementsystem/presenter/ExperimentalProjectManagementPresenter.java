package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.ExperimentalProjectTable;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalProjectManagementModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalProjectManagementView;

import org.json.JSONObject;

public class ExperimentalProjectManagementPresenter extends BasePresenter<ExperimentalProjectManagementView> {

    private ExperimentalProjectManagementModel experimentalProjectManagementModel;

    public ExperimentalProjectManagementPresenter() {
        this.experimentalProjectManagementModel = new ExperimentalProjectManagementModel();
    }

    public void insertData(String experimental_project_code,
                           String experimental_project_name,
                           String experimental_content,
                           String experimental_hours,
                           String experimental_credits,
                           String experimental_properties,
                           String experimental_type,
                           String experimental_category,
                           String affiliation,
                           String subject) {
        this.experimentalProjectManagementModel.insertData(experimental_project_code,
                experimental_project_name,
                experimental_content,
                experimental_hours,
                experimental_credits,
                experimental_properties,
                experimental_type,
                experimental_category,
                affiliation,
                subject, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(ExperimentalProjectTable experimentalProjectTable) {
        this.experimentalProjectManagementModel.insertData(experimentalProjectTable.getExperimental_project_code(),
                experimentalProjectTable.getExperimental_project_name(),
                experimentalProjectTable.getExperimental_content(),
                experimentalProjectTable.getExperimental_hours(),
                experimentalProjectTable.getExperimental_credits(),
                experimentalProjectTable.getExperimental_properties(),
                experimentalProjectTable.getExperimental_type(),
                experimentalProjectTable.getExperimental_category(),
                experimentalProjectTable.getAffiliation(),
                experimentalProjectTable.getSubject(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.experimentalProjectManagementModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
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
        this.experimentalProjectManagementModel.getData(experimental_properties, experimental_type, experimental_category, experimental_project_name, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
