package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginModel {
    private static final String URL_LOGIN = "/user/login";

    public void login(String account, String password, final OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        /*FormBody formBody = new FormBody
                .Builder()
                .add("account", account)
                .add("password", password)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_LOGIN, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });*/
        Map<String, String> map = new HashMap<>();
        map.put("userAccount", account);
        map.put("userPassword", password);
        JSONObject jsonObject = new JSONObject(map);
        OkHttpUtils.post1(jsonObject, URL_LOGIN, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
