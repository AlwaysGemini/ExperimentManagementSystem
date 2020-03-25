package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentalCourseSelectionView extends MVPView {
    void onGetExperimentalCourseListResult(Boolean isSuccess, JSONObject responseJson);

    void onExperimentalCourseSelectionResult(Boolean isSuccess, JSONObject responseJson);
}
