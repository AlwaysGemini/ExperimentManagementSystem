package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.insertBean.InsertCourseExperimentOutline;
import com.gemini.always.experimentmanagementsystem.bean.queryBean.QueryCourseExperimentOutline;
import com.gemini.always.experimentmanagementsystem.model.CourseExperimentOutlineModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentOutlineView;

import org.json.JSONObject;

public class CourseExperimentOutlinePresenter extends BasePresenter<CourseExperimentOutlineView> {

    private CourseExperimentOutlineModel courseExperimentOutlineModel;

    public CourseExperimentOutlinePresenter() {
        this.courseExperimentOutlineModel = new CourseExperimentOutlineModel();
    }

    public void insertData(InsertCourseExperimentOutline insertCourseExperimentOutline) {
        this.courseExperimentOutlineModel.insertData(insertCourseExperimentOutline,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getData(QueryCourseExperimentOutline queryCourseExperimentOutline) {
        this.courseExperimentOutlineModel.getData(queryCourseExperimentOutline, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
