package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface UnmatchedOfExperimentCourseView extends MVPView {
    void onGetUnmatchedListResult(Boolean isSuccess, JSONObject responseJson);

    void onMatchResult(Boolean isSuccess, JSONObject responseJson);
}
