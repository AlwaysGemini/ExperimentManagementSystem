package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface BaseCURDView extends MVPView {
    void onInsertDataResult(Boolean isSuccess, JSONObject responseJson);

    void onGetQueryConditionListResult(Boolean isSuccess, JSONObject responseJson);

    void onGetDataResult(Boolean isSuccess, JSONObject responseJson);

    void onUpdateResult(Boolean isSuccess, JSONObject responseJson);

    void onDeleteResult(Boolean isSuccess, JSONObject responseJson);
}
