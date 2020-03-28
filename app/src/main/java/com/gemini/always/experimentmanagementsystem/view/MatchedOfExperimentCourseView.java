package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface MatchedOfExperimentCourseView extends MVPView {
    void onGetMatchedListResult(Boolean isSuccess, JSONObject responseJson);
}
