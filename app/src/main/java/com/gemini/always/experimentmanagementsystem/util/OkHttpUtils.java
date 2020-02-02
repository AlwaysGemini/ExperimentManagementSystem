package com.gemini.always.experimentmanagementsystem.util;

import com.gemini.always.experimentmanagementsystem.Constants;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.gemini.always.experimentmanagementsystem.Constants.JSON;
import static com.gemini.always.experimentmanagementsystem.Constants.OCTET_STREAM;

public class OkHttpUtils {

    public static JSONObject upload(String url, File file, String fileName, Map<String, String> params) {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, Objects.requireNonNull(params.get(key)));
            }
        }
        builder.addFormDataPart("file", fileName, RequestBody.create
                (OCTET_STREAM, file));
        MultipartBody multipartBody = builder.build();
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .post(multipartBody)
                .build();
        Response response;
        JSONObject responseJson = null;
        try {
            response = client.newCall(request).execute();
            responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            Logger.e(e, "IOException:");
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }
        if (responseJson == null) responseJson = new JSONObject();
        Logger.json(responseJson.toString());
        return responseJson;
    }

    public static JSONObject post(JSONObject requestJson, String url) {

        Logger.json(requestJson.toString());
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, requestJson.toString());
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .post(requestBody)
                .build();

        Response response;
        JSONObject responseJson = null;
        try {
            response = client.newCall(request).execute();
            responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            Logger.e(e, "IOException:");
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }

        if (responseJson == null) responseJson = new JSONObject();
        Logger.json(responseJson.toString());
        return responseJson;
    }

    public static JSONObject get(String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .get()
                .build();

        Response response;
        JSONObject responseJson = null;
        try {
            response = client.newCall(request).execute();
            responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            Logger.e(e, "IOException:");
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }

        if (responseJson == null) responseJson = new JSONObject();
        else {
            try {
                onOkHttpUtilsListener.onResult(responseJson.getString("code").equals("200"), responseJson);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
        Logger.json(responseJson.toString());
        return responseJson;
    }

    public static void post1(JSONObject requestJson, String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {

        Logger.json(requestJson.toString());
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, requestJson.toString());
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .post(requestBody)
                .build();

        Response response;
        JSONObject responseJson = null;
        try {
            response = client.newCall(request).execute();
            responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            Logger.e(e, "IOException:");
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }

        if (responseJson == null) responseJson = new JSONObject();
        else {
            try {
                onOkHttpUtilsListener.onResult(responseJson.getString("code").equals("200"), responseJson);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
        Logger.json(responseJson.toString());
    }

    public static void postByFormBody(FormBody formBody, String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .post(formBody)
                .build();

        Response response;
        JSONObject responseJson = null;
        try {
            response = client.newCall(request).execute();
            responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            Logger.e(e, "IOException:");
        } catch (JSONException e) {
            Logger.e(e, "JSONException:");
        }

        if (responseJson == null) responseJson = new JSONObject();
        else {
            try {
                onOkHttpUtilsListener.onResult(responseJson.getString("code").equals("200"), responseJson);
            } catch (JSONException e) {
                Logger.e(e, "JSONException:");
            }
        }
        Logger.json(responseJson.toString());
    }

    public interface OnOkHttpUtilsListener {
        void onResult(Boolean isSuccess, JSONObject responseJson);
    }
}
