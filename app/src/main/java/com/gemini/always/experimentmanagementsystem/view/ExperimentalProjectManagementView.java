package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface ExperimentalProjectManagementView extends MVPView {
    void onInsertDataResult(Boolean isSuccess, JSONObject responseJson);

    void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson);

    void onGetDataResult(Boolean isSuccess, JSONObject responseJson);
}
