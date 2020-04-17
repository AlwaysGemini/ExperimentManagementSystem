package com.gemini.always.experimentmanagementsystem.util;

import com.gemini.always.experimentmanagementsystem.Constants;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    private static String TAG = "OkHttpUtils";

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
                Logger.e(e, TAG);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException | IOException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                }
            }
        });
    }

    public static void postByJson(JSONObject requestJson, String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {
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
                Logger.e(e, TAG);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                } catch (IOException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                }
            }
        });
    }

    /**
     * get方法
     *
     * @param url
     * @param onOkHttpUtilsListener
     * @return
     */
    public static void get(String url, OnOkHttpUtilsListener onOkHttpUtilsListener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                Logger.e(e, TAG);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                } catch (IOException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                }
            }
        });
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
                Logger.e(e, TAG);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException | IOException e) {
                    call.cancel();
                    Logger.e(e, TAG);
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
                Logger.e(e, TAG);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONObject responseJson = new JSONObject(Objects.requireNonNull(response.body()).string());
                    Logger.json(responseJson.toString());
                    onOkHttpUtilsListener.onResult(true, responseJson);
                } catch (JSONException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                } catch (IOException e) {
                    call.cancel();
                    Logger.e(e, TAG);
                }
            }
        });
    }

    public interface OnOkHttpUtilsListener {
        void onResult(Boolean isSuccess, JSONObject responseJson);
    }

    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称，后面记得拼接后缀，否则手机没法识别文件类型
     * @param listener     下载监听
     */

    public static void download(final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener) {

        Request request = new Request.Builder()
                .url(Constants.URL + url)
                .build();

        OkHttpClient client = new OkHttpClient();

        //异步请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                //储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, destFileName);

                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    //下载完成
                    listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        Logger.e(e, TAG);
                    }
                }
            }
        });
    }


    public interface OnDownloadListener {

        /**
         * 下载成功之后的文件
         */
        void onDownloadSuccess(File file);

        /**
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载异常信息
         */

        void onDownloadFailed(Exception e);
    }
}
