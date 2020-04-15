package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentAchievementExaminingView extends MVPView {
    void onGetExperimentAchievementTableSummaryResult(Boolean isSuccess, JSONObject responseJson);

    void onExaminingResult(Boolean isSuccess, JSONObject responseJson);
}
