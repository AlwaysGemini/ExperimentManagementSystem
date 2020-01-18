package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.bean.User;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;

public class LoginModel {
    private static final String URL_LOGIN = "/user/login";

    public void login(String account, String password, final OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("account",account)
                .add("password",password)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_LOGIN, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
