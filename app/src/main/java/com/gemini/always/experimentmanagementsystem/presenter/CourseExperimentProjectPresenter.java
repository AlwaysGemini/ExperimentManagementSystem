package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.CourseExperimentProjectModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentProjectView;

import org.json.JSONObject;

public class CourseExperimentProjectPresenter extends BasePresenter<CourseExperimentProjectView> {

    private CourseExperimentProjectModel courseExperimentProjectModel;

    public CourseExperimentProjectPresenter() {
        this.courseExperimentProjectModel = new CourseExperimentProjectModel();
    }

    public void getData(String college,
                        String course_category,
                        String course_assignment,
                        String course_enabling_grade,
                        String course) {
        this.courseExperimentProjectModel.getData(college,
                course_category,
                course_assignment,
                course_enabling_grade,
                course,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getQueryConditionList() {
        this.courseExperimentProjectModel.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }
}
