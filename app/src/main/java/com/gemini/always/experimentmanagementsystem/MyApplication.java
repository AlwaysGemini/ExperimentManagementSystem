package com.gemini.always.experimentmanagementsystem;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;
import com.xuexiang.xui.XUI;

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        XUI.init(this); //初始化UI框架
        XUI.debug(true);  //开启UI框架调试日志

        MMKV.initialize(this);

        //初始化Logger
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
