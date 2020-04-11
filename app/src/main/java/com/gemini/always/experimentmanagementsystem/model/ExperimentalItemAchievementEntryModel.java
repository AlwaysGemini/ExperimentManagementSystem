package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.bean.tableTemplateBean.ExperimentalItemAchievementTableTemplate;
import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.FormBody;

public class ExperimentalItemAchievementEntryModel {
    private static final String prefix = "/experimentalItemAchievement";
    private static final String URL_getTemplate = prefix + "/getTemplate";
    private static final String URL_importExperimentalItemAchievement = prefix + "/importExperimentalItemAchievement";

    public void getTemplate(String experiment_course_match_id,
                            OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_course_match_id", experiment_course_match_id)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTemplate, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void importExperimentalItemAchievement(List<ExperimentalItemAchievementTableTemplate> list, OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray(new Gson().toJson(list));
            jsonObject.put("data", jsonArray);
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }

        OkHttpUtils.postByJson(jsonObject, URL_importExperimentalItemAchievement, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
