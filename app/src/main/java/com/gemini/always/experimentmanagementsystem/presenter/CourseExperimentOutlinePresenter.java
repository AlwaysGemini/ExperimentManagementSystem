package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableBean.CourseExperimentOutlineTable;
import com.gemini.always.experimentmanagementsystem.model.CourseExperimentOutlineModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentOutlineView;

import org.json.JSONObject;

public class CourseExperimentOutlinePresenter extends BasePresenter<CourseExperimentOutlineView> {

    private CourseExperimentOutlineModel courseExperimentOutlineModel;

    public CourseExperimentOutlinePresenter() {
        this.courseExperimentOutlineModel = new CourseExperimentOutlineModel();
    }

    public void insertData(String course_id,
                           String proportion_of_experimental_results,
                           String experimental_item_id) {
        this.courseExperimentOutlineModel.insertData(course_id,
                proportion_of_experimental_results,
                experimental_item_id, new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void insertData(CourseExperimentOutlineTable courseExperimentOutlineTable) {
        this.courseExperimentOutlineModel.insertData(courseExperimentOutlineTable.getCourse_id(),
                courseExperimentOutlineTable.getProportion_of_experimental_results(),
                courseExperimentOutlineTable.getExperiment_item_name(), new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onInsertDataResult(isSuccess, responseJson);
                    }
                });
    }

    public void getData(String course) {
        this.courseExperimentOutlineModel.getData(course, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess, responseJson);
            }
        });
    }
}
