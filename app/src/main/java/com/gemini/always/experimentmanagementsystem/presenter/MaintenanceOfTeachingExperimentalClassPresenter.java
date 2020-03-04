package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.MaintenanceOfTeachingExperimentalClassTable;
import com.gemini.always.experimentmanagementsystem.model.MaintenanceOfTeachingExperimentalClassModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.MaintenanceOfTeachingExperimentalClassView;

import org.json.JSONObject;

public class MaintenanceOfTeachingExperimentalClassPresenter extends BasePresenter<MaintenanceOfTeachingExperimentalClassView> {
    private MaintenanceOfTeachingExperimentalClassModel maintenanceOfTeachingExperimentalClassModel;

    public MaintenanceOfTeachingExperimentalClassPresenter() {
        this.maintenanceOfTeachingExperimentalClassModel = new MaintenanceOfTeachingExperimentalClassModel();
    }

    public void insertData(String experimental_teaching_class_id,
                           String experimental_teaching_class_name,
                           String year,
                           String semester,
                           String composition_of_teaching_class,
                           String course_experiment_outline_id) {
        this.maintenanceOfTeachingExperimentalClassModel.insertData(experimental_teaching_class_id,
                experimental_teaching_class_name,
                year,
                semester,
                composition_of_teaching_class,
                course_experiment_outline_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(MaintenanceOfTeachingExperimentalClassTable maintenanceOfTeachingExperimentalClassTable) {
        this.maintenanceOfTeachingExperimentalClassModel.insertData(maintenanceOfTeachingExperimentalClassTable.getYear(),
                maintenanceOfTeachingExperimentalClassTable.getSemester(),
                maintenanceOfTeachingExperimentalClassTable.getExperimental_teaching_class_name(),
                maintenanceOfTeachingExperimentalClassTable.getComposition_of_teaching_class(),
                maintenanceOfTeachingExperimentalClassTable.getCollege(),
                maintenanceOfTeachingExperimentalClassTable.getCourse_chinese_name(),
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.maintenanceOfTeachingExperimentalClassModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String year,
                        String semester,
                        String college,
                        String experimental_teaching_class_name,
                        String course) {
        this.maintenanceOfTeachingExperimentalClassModel.getData(year,
                semester,
                college,
                experimental_teaching_class_name,
                course,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
