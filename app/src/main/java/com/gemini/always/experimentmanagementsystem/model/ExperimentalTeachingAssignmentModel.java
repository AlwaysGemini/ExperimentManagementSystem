package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

/**
 * @Author: 周清
 * @Description:
 * @Date: Created in 9:38 2020/2/8
 */
public class ExperimentalTeachingAssignmentModel {

    private static final String URL_INSERT_DATA = "/experimentalTeachingAssignment/insertData";
    private static final String URL_getQueryConditionList = "/experimentalTeachingAssignment/getQueryConditionList";
    private static final String URL_getTeachingExperimentCenterList = "/experimentalTeachingAssignment/getData";

    public void insertData(String name_of_teaching_class,
                           String teacher,
                           String including_experimental_items,
                           String number_of_electives,
                           OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("name_of_teaching_class", name_of_teaching_class)
                .add("teacher", teacher)
                .add("including_experimental_items", including_experimental_items)
                .add("number_of_electives", number_of_electives)
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

    public void getData(String school_year,
                        String semester,
                        String school_of_commencement,
                        String course,
                        OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("school_year", school_year)
                .add("semester", semester)
                .add("school_of_commencement", school_of_commencement)
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
