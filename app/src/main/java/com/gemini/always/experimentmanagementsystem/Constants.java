package com.gemini.always.experimentmanagementsystem;

import okhttp3.MediaType;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.Constants.java
 * @Description: 存放常量
 * @author: 周清
 * @date: 2020-02-07 21:38
 */
public class Constants {
    //public static final String URL = "http://always.qicp.vip";
    //public static final String URL = "http://39.97.114.3:8080";
    public static final String URL = "http://always.city:8080";

    public static final int REQUEST_CODE_CHOOSE = 23;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType OCTET_STREAM = MediaType.parse("application/octet-stream");
}
