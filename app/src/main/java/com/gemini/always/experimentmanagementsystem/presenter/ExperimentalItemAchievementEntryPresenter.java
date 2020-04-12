package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalItemAchievementEntryModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalItemAchievementEntryView;

import org.json.JSONObject;

public class ExperimentalItemAchievementEntryPresenter extends BasePresenter<ExperimentalItemAchievementEntryView> {
    private ExperimentalItemAchievementEntryModel model;

    public ExperimentalItemAchievementEntryPresenter() {
        this.model = new ExperimentalItemAchievementEntryModel();
    }

    public void getExperimentItemAchievementTableSummary(String user_id) {
        this.model.getExperimentItemAchievementTableSummary(user_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetExperimentItemAchievementTableSummaryResult(isSuccess, responseJson);
                    }
                });
    }

    public void getTemplate(String experiment_course_match_id, String experiment_item_id) {
        this.model.getTemplate(experiment_course_match_id,
                experiment_item_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {
                        getView().onGetTemplateResult(isSuccess, responseJson);
                    }
                });
    }

    public void importExperimentalItemAchievement(String filePath,
                                                  String fileName,
                                                  String experiment_item_id,
                                                  String experiment_course_match_id) {
        this.model.importExperimentalItemAchievement(filePath,
                fileName,
                experiment_item_id,
                experiment_course_match_id,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {

                    }
                });
    }
}
