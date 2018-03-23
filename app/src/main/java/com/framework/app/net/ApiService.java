package com.framework.app.net;


import com.framework.app.bean.PlatformBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口
 */

public interface ApiService {

    //平台列表

    @GET("satinApi")
    Observable<PlatformBean> getPlatforms(@Query("type") String type,@Query("page") String page);

}
