package com.gemini.always.experimentmanagementsystem.util;

import com.gemini.always.experimentmanagementsystem.Constants;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.gemini.always.experimentmanagementsystem.Constants.JSON;
import static com.gemini.always.experimentmanagementsystem.Constants.OCTET_STREAM;

/**
 * @version V1.0
 * @Title:
 * @ClassName: com.gemini.always.experimentmanagementsystem.util.OkHttpUtils.java
 * @Description:
 * @author: 周清
 * @date: 2020-02-07 21:38
 */
public class OkHttpUtils {

    /**
     * 上传文件的方法
     *
     * @param url
     * @param file
     * @param fileName
     * @param params
     * @return
     */
    public static void upload(String url, File file, String fileName, Map<String, String> params, OkHttpUtils.OnOkHttpUtilsListener onOkHttpUtilsListener) {
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

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Logger.e(e, "IOException:");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException e) {
                    call.cancel();
                    Logger.e(e, "JSONException:");
                } catch (IOException e) {
                    call.cancel();
                    Logger.e(e, "IOException:");
                }
            }
        });
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

    /**
     * get方法
     *
     * @param url
     * @param onOkHttpUtilsListener
     * @return
     */
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

    /**
     * 以Json字符串的形式通过POST方法向接口请求数据
     *
     * @param requestJson
     * @param url
     * @param onOkHttpUtilsListener
     */
    public static void post1(JSONObject requestJson, String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {

        Logger.json(requestJson.toString());
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, requestJson.toString());
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Logger.e(e, "IOException:");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException e) {
                    call.cancel();
                    Logger.e(e, "JSONException:");
                } catch (IOException e) {
                    call.cancel();
                    Logger.e(e, "IOException:");
                }
            }
        });
    }

    /**
     * 以表单的形式通过POST方法向接口请求数据
     *
     * @param formBody
     * @param url
     * @param onOkHttpUtilsListener
     */
    public static void postByFormBody(FormBody formBody, String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formBody.size(); i++) {
            sb.append(formBody.name(i) + "=" + formBody.value(i) + "\n");
        }
        Logger.d(sb.toString());

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Logger.e(e, "IOException:");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException e) {
                    call.cancel();
                    Logger.e(e, "JSONException:");
                } catch (IOException e) {
                    call.cancel();
                    Logger.e(e, "IOException:");
                }
            }
        });
    }

    public interface OnOkHttpUtilsListener {
        void onResult(Boolean isSuccess, JSONObject responseJson);
    }
}
