package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;
import com.tencent.mmkv.MMKV;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

public class ExperimentProjectInstructionUploadModel {
    private static final String prefix = "/experimentProjectInstruction";
    private static final String URL_getExperimentProjectInstructionState = prefix + "/getExperimentProjectInstructionState";
    private static final String URL_upload = prefix + "/upload";

    public void getExperimentProjectInstructionState(OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        FormBody formBody = new FormBody
                .Builder()
                .add("user_id", MMKV.defaultMMKV().getString("user_id", ""))
                .build();

        OkHttpUtils.postByFormBody(formBody, URL_getExperimentProjectInstructionState, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {
                onOkHttpUtilsListener.onResult(isSuccess, responseJson);
            }
        });
    }

    public void uploadExperimentProjectInstruction(String filePath,
                                                   String fileName,
                                                   String experiment_project_instruction_id,
                                                   OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        Map<String, String> map = new HashMap<>();
        map.put("experiment_project_instruction_id", experiment_project_instruction_id);
        OkHttpUtils.upload(URL_upload, new File(filePath), fileName, map, new OkHttpUtils.OnOkHttpUtilsListener() {
            @Override
            public void onResult(Boolean isSuccess, JSONObject responseJson) {

            }
        });
    }
}
