package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONObject;

import okhttp3.FormBody;

public class ExperimentProjectInstructionExaminingModel {
    private static final String prefix = "/experimentProjectInstruction";
    private static final String URL_getExperimentProjectInstructionState = prefix + "/getExperimentProjectInstructionState";
    private static final String URL_examining = prefix + "/examining";

    public void getExperimentProjectInstructionState(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("user_id", "")
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getExperimentProjectInstructionState, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void examining(String experiment_project_instruction_id,
                          String state,
                          OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("experiment_project_instruction_id", experiment_project_instruction_id)
                .add("state", state)
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_examining, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }
}
