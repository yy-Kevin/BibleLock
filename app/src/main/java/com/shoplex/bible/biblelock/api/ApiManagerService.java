package com.shoplex.bible.biblelock.api;

import com.shoplex.bible.biblelock.bean.Result;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by qsk on 2017/4/17.
 */

public interface ApiManagerService {

    @POST("/biz/bizserver/news/list.do")
    Observable<Result> getReslut(@QueryMap Map<String, String> option);

    //GET请求
    @GET("/bjws/app.user/login")
    Observable<Result> getVerfcationGet(@Query("tel") String tel, @Query("password") String pass);

    //GET请求
    @GET("books/{id}")
    Observable<Result> getReslut(@Path("id") int id);


    //GET请求，设置缓存
    @Headers("Cache-Control: public," )
    @GET("/bjws/app.user/login")
    Observable<Result> getVerfcationGetCache(@Query("tel") String tel, @Query("password") String pass);
}
