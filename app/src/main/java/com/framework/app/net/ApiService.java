package com.framework.app.net;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 接口
 */

public interface ApiService {

    @GET("product/listNew.json")
    Observable<Object> getData();

}
