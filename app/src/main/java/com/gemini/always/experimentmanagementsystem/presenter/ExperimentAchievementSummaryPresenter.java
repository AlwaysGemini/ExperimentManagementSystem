package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentAchievementSummaryModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentAchievementSummaryView;

import org.json.JSONObject;

public class ExperimentAchievementSummaryPresenter extends BasePresenter<ExperimentAchievementSummaryView> {
    private ExperimentAchievementSummaryModel model;

    public ExperimentAchievementSummaryPresenter() {
        this.model = new ExperimentAchievementSummaryModel();
    }

    public void getExperimentalAchievementSummary(String user_id) {
        model.getExperimentalAchievementSummary(user_id, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetExperimentalAchievementSummaryResult(isSuccess, responseJson);
            }
        });
    }
}
