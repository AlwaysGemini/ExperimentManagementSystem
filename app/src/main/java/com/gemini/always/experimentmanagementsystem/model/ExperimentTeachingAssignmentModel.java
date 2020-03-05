package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:38 2020/2/8
 */
public class ExperimentTeachingAssignmentModel {

    private static final String URL_INSERT_DATA = "/experimentalTeachingAssignment/insertData";
    private static final String URL_getQueryConditionList = "/experimentalTeachingAssignment/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalTeachingAssignment/getData";

    public void insertData(String experimental_teaching_class_id,
                           String teacher_id,
                           String experiment_item_name,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experimental_teaching_class_id", experimental_teaching_class_id)
                .add("teacher_id", teacher_id)
                .add("experiment_item_name", experiment_item_name)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_INSERT_DATA, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getQueryConditionList(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getQueryConditionList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void getData(String year,
                        String semester,
                        String college,
                        String course,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("year", year)
                .add("semester", semester)
                .add("college", college)
                .add("course", course)
                .build();
        OkHttpUtils.postByFormBody(formBody, URL_getTeachingExperimentCenterList, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
