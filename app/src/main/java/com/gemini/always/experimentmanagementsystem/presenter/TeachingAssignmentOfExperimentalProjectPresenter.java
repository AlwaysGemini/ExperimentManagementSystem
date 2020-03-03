package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.TeachingAssignmentOfExperimentalProjectTable;
import com.gemini.always.experimentmanagementsystem.model.TeachingAssignmentOfExperimentalProjectModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.BaseCURDView;

import org.json.JSONObject;

public class TeachingAssignmentOfExperimentalProjectPresenter extends BasePresenter<BaseCURDView> {
    private TeachingAssignmentOfExperimentalProjectModel model;

    public TeachingAssignmentOfExperimentalProjectPresenter() {
        this.model = new TeachingAssignmentOfExperimentalProjectModel();
    }

    public void insertData(String name_of_teaching_class,
                           String number_of_elective_courses) {
        model.insertData(name_of_teaching_class,
                number_of_elective_courses,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(TeachingAssignmentOfExperimentalProjectTable tableItem) {
        model.insertData(tableItem.getName_of_teaching_class(),
                tableItem.getNumber_of_elective_courses(),
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.model.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String school_year,
                        String semester,
                        String school_of_commencement,
                        String course) {
        this.model.getData(school_year,
                semester,
                school_of_commencement,
                course,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
