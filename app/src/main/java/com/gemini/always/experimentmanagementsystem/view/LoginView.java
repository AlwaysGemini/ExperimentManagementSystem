package com.gemini.always.experimentmanagementsystem.view;

import com.gemini.always.experimentmanagementsystem.base.MVPView;

import org.json.JSONObject;

public interface LoginView extends MVPView {
    void onLoginResult(Boolean isSuccess, JSONObject responseJson);
}
