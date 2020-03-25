package com.gemini.always.experimentmanagementsystem.bean;

import android.content.Context;

import com.gemini.always.experimentmanagementsystem.util.ACache;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String userName;
    private String userType;
    private String userSex;
    private String userAge;

    public static void login(Context context, JSONObject responseJson) {
        if (MMKV.defaultMMKV().decodeString("account") == null) {
            try {
                User user = new User();
                user.setUserId(responseJson.getJSONObject("data").getString("user_id"));
                user.setUserName(responseJson.getJSONObject("data").getString("user_name"));
                user.setUserSex(responseJson.getJSONObject("data").getString("user_sex"));
                user.setUserAge(responseJson.getJSONObject("data").getString("user_age"));
                user.setUserType(responseJson.getJSONObject("data").getString("user_role"));
                ACache.get(context).put("user", user);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
    }

    public static boolean isLogin(Context context) {
        return !(ACache.get(context).getAsObject("user") == null);
    }

    public static void logout(Context context) {
        ACache.get(context).clear();
    }

    public static User getCurrentUser(Context context) {
        return ((User) (ACache.get(context).getAsObject("user")));
    }

    public static String getCurrentUserType(Context context) {
        return getCurrentUser(context).getUserType();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void setCurrentUserType(Context context, String userType) {
        User user = getCurrentUser(context);
        user.setUserType(userType);
        ACache.get(context).put("user", user);
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
