package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.CourseExperimentProjectTable;
import com.gemini.always.experimentmanagementsystem.model.CourseExperimentProjectModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.CourseExperimentProjectView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CourseExperimentProjectPresenter extends BasePresenter<CourseExperimentProjectView> {

    private CourseExperimentProjectModel courseExperimentProjectModel;

    public CourseExperimentProjectPresenter() {
        this.courseExperimentProjectModel = new CourseExperimentProjectModel();
    }

    public void getData() {
        this.courseExperimentProjectModel.getData(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetDataResult(isSuccess,responseJson);
            }
        });
    }
}
