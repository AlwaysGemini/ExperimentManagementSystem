package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentSchedulingView extends MVPView {
    void onInsertDataResult(Boolean isSuccess, JSONObject responseJson);

    void getFreeTimeDataResult(Boolean isSuccess, JSONObject responseJson);
}
