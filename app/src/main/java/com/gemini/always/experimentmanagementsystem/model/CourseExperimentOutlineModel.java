package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.bean.insertBean.InsertCourseExperimentOutline;
import com.gemini.always.experimentmanagementsystem.bean.queryBean.QueryCourseExperimentOutline;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseExperimentOutlineModel {

    private static String TAG = "CourseExperimentOutlineModel";

    private static final String URL_INSERT_DATA = "/courseExperimentOutline/insertData";
    private static final String URL_getTeachingExperimentCenterList = "/courseExperimentOutline/getData";

    public void insertData(InsertCourseExperimentOutline insertCourseExperimentOutline,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new Gson().toJson(insertCourseExperimentOutline));
        } catch (JSONException e) {
            Logger.e(e, TAG);
        }
        OkHttpUtils.postByJson(jsonObject, URL_INSERT_DATA, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(QueryCourseExperimentOutline queryCourseExperimentOutline,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new Gson().toJson(queryCourseExperimentOutline));
        } catch (JSONException e) {
            Logger.e(e, TAG);
        }
        OkHttpUtils.postByJson(jsonObject, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void delete(QueryCourseExperimentOutline queryCourseExperimentOutline,
                       OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {

    }
}
