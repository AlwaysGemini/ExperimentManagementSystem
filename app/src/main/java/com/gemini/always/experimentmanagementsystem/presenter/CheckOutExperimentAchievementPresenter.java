package com.gemini.always.experimentmanagementsystem.presenter;

import com.gemini.always.experimentmanagementsystem.base.BasePresenter;
import com.gemini.always.experimentmanagementsystem.model.CheckOutExperimentAchievementModel;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.gemini.always.experimentmanagementsystem.view.CheckOutExperimentAchievementView;

import org.json.JSONObject;

public class CheckOutExperimentAchievementPresenter extends BasePresenter<CheckOutExperimentAchievementView> {
    private CheckOutExperimentAchievementModel model;

    public CheckOutExperimentAchievementPresenter(){
        this.model = new CheckOutExperimentAchievementModel();
    }

    public void checkOutAchievement(String student_id){
        this.model.checkOutAchievement(student_id,new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                getView().onGetExperimentAchievementResult(isSuccess, responseJson);
            }
        });
    }
}
