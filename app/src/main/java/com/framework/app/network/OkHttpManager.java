package com.framework.app.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.framework.app.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpManager {

    public static String HTTP_URL = "http://116.62.140.28:9083";
    public static String REQUEST_OK_CODE = "0";
    public static final String REQUEST_ERROR_CODE = "-1000";

    private static final MediaType MediaType_JSON = MediaType.parse("application/json;charset=utf-8");
    private static final long connectTimeout = 20;
    private static final long readTimeout = 20;
    private volatile static OkHttpManager manager;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

    private static final String NET_NO_MSG = "没有网络连接";
    private static final String NET_ERR_MSG = "连接服务器失败";

    private OkHttpManager() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .sslSocketFactory(getSslContext().getSocketFactory(), getX509TrustManager())
                .hostnameVerifier(getNotVerifyHostnameVerifier())
                .build();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    /**
     * 设置https 访问的时候对所有证书都进行信任，忽略证书校验
     *
     * @return
     */
    private SSLContext getSslContext() {
        X509TrustManager xtm = getX509TrustManager();
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext;
    }

    /**
     * /**
     * 设置https 访问的时候对所有证书都进行信任,忽略证书校验
     *
     * @return
     */
    private X509TrustManager getX509TrustManager() {
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        return x509TrustManager;
    }

    private HostnameVerifier getNotVerifyHostnameVerifier() {
        HostnameVerifier notVerifyHostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        return notVerifyHostnameVerifier;
    }

    public static OkHttpManager getManager() {
        if (manager == null) {
            synchronized (OkHttpManager.class) {
                if (manager == null) {
                    manager = new OkHttpManager();
                }
            }
        }
        return manager;
    }

    /**
     * get 或者 post 提交数据
     *
     * @param baseRequest
     * @param onHttpCallBack
     */
    public Call getHttpData(final BaseRequest baseRequest, final OnHttpCallBack onHttpCallBack) {
        Call call = null;
        try {
            //一种写法**这个方法开启了线程可以在住线程里直接调用
            call = mOkHttpClient.newCall(createRequest(baseRequest));
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    e.printStackTrace();
                    if (!call.isCanceled() && onHttpCallBack != null) {
                        //没网
                        setErrorHttpCallBack(onHttpCallBack);
                    }
                }

                @Override
                public void onResponse(Call call, final Response response) {
                    try {
                        final String jsonStr = response.body().string();
                        LogUtil.i(jsonStr);
                        if (onHttpCallBack != null) {
                            mDelivery.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.isSuccessful()) {
                                        BaseResponse baseResponse = getBaseResponse(jsonStr);
                                        if (REQUEST_OK_CODE.equals(baseResponse.code)) {
                                            onHttpCallBack.onSuccess(jsonStr, baseResponse.code, baseRequest);
                                        } else {
                                            onHttpCallBack.onFail(baseResponse.msg, baseResponse.code);
                                        }
                                    } else {
                                        onHttpCallBack.onFail(NET_ERR_MSG, String.valueOf(response.code()));
                                    }
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        setErrorHttpCallBack(onHttpCallBack);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            setErrorHttpCallBack(onHttpCallBack);
        }
        return call;
    }

    /**
     * get 或者 post 提交数据
     *
     * @param baseRequest
     * @param onHttpCallBack
     */
    public Call uploadFile(final BaseRequest baseRequest, final OnHttpCallBack onHttpCallBack, final File file) {
        Call call = null;
        try {
            //一种写法**这个方法开启了线程可以在住线程里直接调用
            call = mOkHttpClient.newCall(createFileUpLoadRequest(baseRequest, file));
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    e.printStackTrace();
                    if (!call.isCanceled() && onHttpCallBack != null) {
                        //没网
                        setErrorHttpCallBack(onHttpCallBack);
                    }
                }

                @Override
                public void onResponse(Call call, final Response response) {
                    try {
                        final String jsonStr = response.body().string();
                        if (onHttpCallBack != null) {
                            mDelivery.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (response.isSuccessful()) {
                                        BaseResponse baseResponse = getBaseResponse(jsonStr);
                                        if (REQUEST_OK_CODE.equals(baseResponse.code)) {
                                            onHttpCallBack.onSuccess(jsonStr, baseResponse.msg, baseRequest);
                                        } else {
                                            onHttpCallBack.onFail(baseResponse.msg, baseResponse.code);
                                        }
                                    } else {
                                        onHttpCallBack.onFail(NET_ERR_MSG, String.valueOf(response.code()));
                                    }
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        setErrorHttpCallBack(onHttpCallBack);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            setErrorHttpCallBack(onHttpCallBack);
        }
        return call;
    }

    private void setErrorHttpCallBack(final OnHttpCallBack onHttpCallBack) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                onHttpCallBack.onFail(NET_ERR_MSG, REQUEST_ERROR_CODE + "");
            }
        });
    }

    private BaseResponse getBaseResponse(String json) {
        BaseResponse baseResponse = null;
        try {
            baseResponse = JSONObject.parseObject(json, BaseResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (baseResponse == null) {
            baseResponse = new BaseResponse(REQUEST_ERROR_CODE, "失败");
        }
        return baseResponse;
    }


    /**
     * 生成 okHttp 需要的request
     *
     * @param baseRequest
     * @return
     */
    private Request createRequest(BaseRequest baseRequest) {
        Request request = null;
        String urlString = HTTP_URL + baseRequest.urlEnum.url;

        Request.Builder requestBuilder = new Request.Builder();
        addRequestHeader(baseRequest, requestBuilder);
        Map<String, Object> params = baseRequest.params;
        if (UrlEnum.RequestMethod.GET.equals(baseRequest.urlEnum.requestMethod)) {
            //GET
            urlString = setUrlParams(urlString, params);
            LogUtil.i(urlString);
            request = requestBuilder.url(urlString).build();
        } else if (UrlEnum.RequestMethod.POST.equals(baseRequest.urlEnum.requestMethod)) {
            //POST
            /*String json = JSONObject.toJSONString(params);
            RequestBody body = RequestBody.create(MediaType_JSON, json);
            showPostParams(params);*/

            RequestBody body = changeParams2RequestBody(params);
            request = requestBuilder.url(urlString).post(body).build();
        }
        return request;
    }

    private RequestBody changeParams2RequestBody(Map<String, Object> params) {
        FormBody.Builder requestBodBuilder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object values = entry.getValue();
            LogUtil.i("参数：" + key + "-----值：" + values);
            if (values != null) {
                requestBodBuilder.add(key, values.toString());
            }
        }
        return requestBodBuilder.build();
    }

    private void addRequestHeader(BaseRequest baseRequest, Request.Builder requestBuilder) {
        Map<String, Object> headerParams = baseRequest.headerParams;
        if (headerParams == null || headerParams.size() == 0) return;
        for (Map.Entry<String, Object> entry : headerParams.entrySet()) {
            String key = entry.getKey();
            String values = String.valueOf(entry.getValue());
            requestBuilder.addHeader(key, values);
        }
    }


    /**
     * 生成 okHttp 上传文件，需要的request
     *
     * @param baseRequest
     * @return
     */
    private Request createFileUpLoadRequest(BaseRequest baseRequest, final File file) {
        Request request = null;
        String urlString = HTTP_URL + baseRequest.urlEnum.url;
        Request.Builder requestBuilder = new Request.Builder();
        addRequestHeader(baseRequest, requestBuilder);
        Map<String, Object> params = baseRequest.params;
        if (UrlEnum.RequestMethod.POST.equals(baseRequest.urlEnum.requestMethod)) {
            //POST
            MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            body.addFormDataPart("file", file.getName(), fileBody);
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object values = entry.getValue();
                //设置参数
                body.addFormDataPart(key, String.valueOf(values));
                LogUtil.i("参数：" + key + "-----值：" + values);
            }
            request = requestBuilder.url(urlString).post(body.build()).build();
        }
        return request;
    }

    private String setUrlParams(String url, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            if (url.contains("?")) {
                stringBuilder.append("&");
            } else {
                stringBuilder.append("?");
            }
            stringBuilder.append(changeParams2String(params));
            url = url + stringBuilder;
        }
        LogUtil.i(url);
        return url;
    }

    /**
     * 打印json数据请求的参数
     *
     * @param params
     */
    private void showPostParams(Map<String, Object> params) {

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object values = entry.getValue();
            LogUtil.i("参数：" + key + "-----：" + values);
        }

    }

    private StringBuffer changeParams2String(Map<String, Object> params) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            try {
                String key = entry.getKey();
                String values = String.valueOf(entry.getValue());
                LogUtil.i("参数：" + key + "-----值：" + values);
                if (values != null) {
                    stringBuffer.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(values)).append("&");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stringBuffer.length() >= 1) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer;
    }


    public interface OnHttpCallBack {
        /**
         * @param jsonStr 返回json数据
         * @param code    返回状态码
         */
        void onSuccess(String jsonStr, String code, BaseRequest request);

        /**
         * @param errMsg 错误信息
         * @param code   错误码
         */
        void onFail(String errMsg, String code);
    }

}
