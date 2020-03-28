package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.UnmatchedOfExperimentCourseModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.UnmatchedOfExperimentCourseView;

import org.json.JSONObject;

public class UnmatchedOfExperimentCoursePresenter extends BasePresenter<UnmatchedOfExperimentCourseView> {
    private UnmatchedOfExperimentCourseModel model;

    public UnmatchedOfExperimentCoursePresenter() {
        this.model = new UnmatchedOfExperimentCourseModel();
    }

    public void getUnmatchedList() {
        model.getUnmatchedList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetUnmatchedListResult(isSuccess, responseJson);
            }
        });
    }

    public void match(String experimental_teaching_class_id,
                      String capacity) {
        model.match(experimental_teaching_class_id,
                capacity,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onMatchResult(isSuccess, responseJson);
                    }
                });
    }
}
