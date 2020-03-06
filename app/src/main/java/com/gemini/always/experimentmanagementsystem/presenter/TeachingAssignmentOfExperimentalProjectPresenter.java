package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.TeachingAssignmentOfExperimentalProjectModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.BaseCURDView;

import org.json.JSONObject;

public class TeachingAssignmentOfExperimentalProjectPresenter extends BasePresenter<BaseCURDView> {
    private TeachingAssignmentOfExperimentalProjectModel model;

    public TeachingAssignmentOfExperimentalProjectPresenter() {
        this.model = new TeachingAssignmentOfExperimentalProjectModel();
    }

    public void getQueryConditionList() {
        this.model.getQueryConditionList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetQueryConditionListResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String year,
                        String semester,
                        String college,
                        String course) {
        this.model.getData(year,
                semester,
                college,
                course,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetDataResult(isSuccess, responseJson);
                    }
                });
    }
}
