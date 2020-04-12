package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentAchievementSummaryView extends MVPView {
    void onGetExperimentalAchievementSummaryResult(Boolean isSuccess, JSONObject responseJson);
}
