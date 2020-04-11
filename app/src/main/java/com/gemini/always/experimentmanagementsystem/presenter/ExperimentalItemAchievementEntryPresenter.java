package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.bean.tableTemplateBean.ExperimentalItemAchievementTableTemplate;
import com.gemini.always.experimentmanagementsystem.model.ExperimentalItemAchievementEntryModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.ExperimentalItemAchievementEntryView;

import org.json.JSONObject;

import java.util.List;

public class ExperimentalItemAchievementEntryPresenter extends BasePresenter<ExperimentalItemAchievementEntryView> {
    private ExperimentalItemAchievementEntryModel model;

    public ExperimentalItemAchievementEntryPresenter() {
        this.model = new ExperimentalItemAchievementEntryModel();
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

    public void importExperimentalItemAchievement(List<ExperimentalItemAchievementTableTemplate> list) {
        this.model.importExperimentalItemAchievement(list,
                new OkHttpUtils.OnOkHttpUtilsListener() {
                    @Override
                    public void onResult(Boolean isSuccess, JSONObject responseJson) {

                    }
                });
    }
}
