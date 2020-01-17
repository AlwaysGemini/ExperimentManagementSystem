package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface CourseExperimentProjectView extends MVPView {
    void onGetDataResult(Boolean isSuccess, JSONObject responseJson);
}
