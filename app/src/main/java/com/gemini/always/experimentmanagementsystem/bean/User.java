package com.gemini.always.experimentmanagementsystem.bean;

import com.tencent.mmkv.MMKV;

public class User {
    private String userId;
    private String userName;
    private String userType;
    private String userSex;
    private String userPhoneNumber;

    public static void login(String account) {
        if (MMKV.defaultMMKV().decodeString("account") == null) {
            MMKV.defaultMMKV().encode("account", account);
        }
    }

    public static boolean isLogin() {
        return !(MMKV.defaultMMKV().decodeString("account") == null);
    }

    public static void logout() {
        if (MMKV.defaultMMKV().decodeString("account") != null) {
            MMKV.defaultMMKV().remove("account");
        }
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
