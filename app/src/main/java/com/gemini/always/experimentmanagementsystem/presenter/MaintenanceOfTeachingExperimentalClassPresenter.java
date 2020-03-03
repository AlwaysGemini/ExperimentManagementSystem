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

    public void insertData(String school_year,
                           String semester,
                           String name_of_teaching_class,
                           String composition_of_teaching_class,
                           String school_of_commencement,
                           String course_name) {
        this.maintenanceOfTeachingExperimentalClassModel.insertData(school_year,
                semester,
                name_of_teaching_class,
                composition_of_teaching_class,
                school_of_commencement,
                course_name,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(MaintenanceOfTeachingExperimentalClassTable maintenanceOfTeachingExperimentalClassTable) {
        this.maintenanceOfTeachingExperimentalClassModel.insertData(maintenanceOfTeachingExperimentalClassTable.getSchool_year(),
                maintenanceOfTeachingExperimentalClassTable.getSemester(),
                maintenanceOfTeachingExperimentalClassTable.getName_of_teaching_class(),
                maintenanceOfTeachingExperimentalClassTable.getComposition_of_teaching_class(),
                maintenanceOfTeachingExperimentalClassTable.getSchool_of_commencement(),
                maintenanceOfTeachingExperimentalClassTable.getCourse_name(),
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

    public void getData(String school_year,
                        String semester,
                        String school_of_commencement,
                        String name_of_teaching_class,
                        String course) {
        this.maintenanceOfTeachingExperimentalClassModel.getData(school_year,
                semester,
                school_of_commencement,
                name_of_teaching_class,
                course,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
