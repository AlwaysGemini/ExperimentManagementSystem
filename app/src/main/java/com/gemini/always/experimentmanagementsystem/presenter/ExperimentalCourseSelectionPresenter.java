package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalCourseSelectionModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalCourseSelectionView;

import org.json.JSONObject;

public class ExperimentalCourseSelectionPresenter extends BasePresenter<ExperimentalCourseSelectionView> {
    private ExperimentalCourseSelectionModel model;

    public ExperimentalCourseSelectionPresenter() {
        this.model = new ExperimentalCourseSelectionModel();
    }

    public void getExperimentalCourseList() {
        model.getExperimentalCourseList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetExperimentalCourseListResult(isSuccess, responseJson);
            }
        });
    }

    public void select(String student_id,
                       String experiment_course_match_id) {
        model.select(student_id,
                experiment_course_match_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onExperimentalCourseSelectionResult(isSuccess, responseJson);
                    }
                });
    }
}
