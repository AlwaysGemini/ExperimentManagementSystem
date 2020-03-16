package com.gemini.always.experimentmanagementsystem.bean;

import com.tencent.mmkv.MMKV;

public class User {
    private String userId;
    private String userName;
    private String userType;
    private String userSex;
    private String userPhoneNumber;

    public static void login(String account, String user_id, String userType) {
        if (MMKV.defaultMMKV().decodeString("account") == null) {
            MMKV.defaultMMKV().encode("account", account);
            MMKV.defaultMMKV().encode("user_id", user_id);
            MMKV.defaultMMKV().encode("userType", userType);
        }
    }

    public static boolean isLogin() {
        return !(MMKV.defaultMMKV().decodeString("account") == null);
    }

    public static void logout() {
        if (MMKV.defaultMMKV().decodeString("account") != null) {
            MMKV.defaultMMKV().clearAll();
        }
    }

    public static String getUserType() {
        return MMKV.defaultMMKV().getString("userType", "");
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

    public static void setUserType(String userType) {
        MMKV.defaultMMKV().encode("userType", userType);
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
