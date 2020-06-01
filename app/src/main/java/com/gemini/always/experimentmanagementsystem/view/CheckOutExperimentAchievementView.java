package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface CheckOutExperimentAchievementView extends MVPView {
    void onGetExperimentAchievementResult(Boolean isSuccess, JSONObject responseJson);
}
