package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentAchievementExaminingModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentAchievementExaminingView;

import org.json.JSONObject;

public class ExperimentAchievementExaminingPresenter extends BasePresenter<ExperimentAchievementExaminingView> {
    private ExperimentAchievementExaminingModel model;

    public ExperimentAchievementExaminingPresenter() {
        this.model = new ExperimentAchievementExaminingModel();
    }

    public void getExperimentItemAchievementTableSummary(String user_id) {
        this.model.getExperimentAchievementTableSummary(user_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetExperimentAchievementTableSummaryResult(isSuccess, responseJson);
                    }
                });
    }

    public void examining(String experiment_course_match_id,
                          String experiment_achievement_table_state) {
        this.model.examining(experiment_course_match_id,
                experiment_achievement_table_state,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetExperimentAchievementTableSummaryResult(isSuccess, responseJson);
                    }
                });
    }
}
