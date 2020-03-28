package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.MatchedOfExperimentCourseModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.MatchedOfExperimentCourseView;

import org.json.JSONObject;

public class MatchedOfExperimentCoursePresenter extends BasePresenter<MatchedOfExperimentCourseView> {
    private MatchedOfExperimentCourseModel model;

    public MatchedOfExperimentCoursePresenter() {
        this.model = new MatchedOfExperimentCourseModel();
    }

    public void getMatchedList() {
        model.getUnmatchedList(new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetMatchedListResult(isSuccess, responseJson);
            }
        });
    }
}
