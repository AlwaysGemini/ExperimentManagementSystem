package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentalItemAchievementEntryView extends MVPView {
    void onGetTemplateResult(Boolean isSuccess, JSONObject responseJson);

    void onImportExperimentalItemAchievementResult(Boolean isSuccess, JSONObject responseJson);
}
