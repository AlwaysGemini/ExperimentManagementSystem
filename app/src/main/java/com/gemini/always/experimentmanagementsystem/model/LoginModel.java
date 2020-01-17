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

    public User saveUserInfo(JSONObject data) {
        User user = new User();
        try {
            if (data.has("userType")) {
                user.setToken(data.getString("uid"));
                user.setUserName(data.getString("name"));
                switch (data.getString("userType")) {
                    case "1":
                        user.setUserType("学生");
                        break;
                    case "2":
                        user.setUserType("辅导员");
                        break;
                    case "3":
                        user.setUserType("教师");
                        break;
                    default:
                        user.setUserType("未知");
                        break;
                }
                user.setUserId(data.getString("id"));
                if (data.getString("gender").equals("1")) {
                    user.setUserSex("男");
                } else if (data.getString("gender").equals("0")) {
                    user.setUserSex("女");
                }
                user.setUserPhoneNumber(data.getString("phoneNumber"));
            } else {
                user.setToken(data.getString("uid"));
            }
            return user;
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }
        return null;
    }
}
