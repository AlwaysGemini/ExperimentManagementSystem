package com.gemini.always.experimentmanagementsystem.model;

import com.gemini.always.experimentmanagementsystem.util.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.FormBody;

public class ExperimentProjectInstructionCheckModel {
    private static final String prefix = "/experimentProjectInstruction";
    private static final String URL_getExperimentProjectInstructionState = prefix + "/getExperimentProjectInstructionState";
    private static final String URL_download = prefix + "/download";

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

    public void download(final String url, final String destFileDir, final String destFileName,
                         OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject("{msg:下载成功}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject finalJsonObject = jsonObject;
        OkHttpUtils.download(url, destFileDir, destFileName, new OkHttpUtils.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                onOkHttpUtilsListener.onResult(true, finalJsonObject);
            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed(Exception e) {
                onOkHttpUtilsListener.onResult(false, finalJsonObject);
            }
        });
    }
}
