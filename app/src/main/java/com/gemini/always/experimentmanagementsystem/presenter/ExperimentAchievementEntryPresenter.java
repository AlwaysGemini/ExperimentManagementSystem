package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentAchievementEntryModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentAchievementEntryView;

import org.json.JSONObject;

public class ExperimentAchievementEntryPresenter extends BasePresenter<ExperimentAchievementEntryView> {
    private ExperimentAchievementEntryModel model;

    public ExperimentAchievementEntryPresenter() {
        this.model = new ExperimentAchievementEntryModel();
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

    public void getTemplate(String experiment_course_match_id) {
        this.model.getTemplate(experiment_course_match_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetTemplateResult(isSuccess, responseJson);
                    }
                });
    }

    public void importExperimentalItemAchievement(String filePath,
                                                  String fileName,
                                                  String experiment_course_match_id) {
        this.model.importExperimentAchievement(filePath,
                fileName,
                experiment_course_match_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {

                    }
                });
    }
}
