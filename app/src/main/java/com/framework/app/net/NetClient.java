package com.framework.app.net;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2018/1/29.
 */

public class NetClient {

    private final long TIME_OUT = 5 * 1000;
    private static NetClient sClient;
    private static final String BASE_URL = "https://www.apiopen.top/";

    private Retrofit mRetrofit;

    private NetClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHttpClient())
                .addConverterFactory(MyConverterFavtory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NetClient getInstance() {
        if (sClient == null) {
            synchronized (NetClient.class) {
                if (sClient == null) {
                    sClient = new NetClient();
                }
            }
        }
        return sClient;
    }

    public ApiService net() {
        return mRetrofit.create(ApiService.class);
    }




    private OkHttpClient getHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.proxy(Proxy.NO_PROXY);
        okHttpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS);

        //okHttpClient.addInterceptor(new HeaderInterceptor());
        okHttpClient.addInterceptor(new LoggingInterceptor());

        return okHttpClient.build();
    }

}
