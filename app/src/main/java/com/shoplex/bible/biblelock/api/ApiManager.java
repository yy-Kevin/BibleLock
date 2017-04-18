package com.shoplex.bible.biblelock.api;

import com.shoplex.bible.biblelock.MyApplication;
import com.shoplex.bible.biblelock.location.LoactionUrl;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qsk on 2017/4/17.
 */

public class ApiManager {
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    //设置缓存目录
    private static File cacheDirectory = new File(MyApplication.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    public static ApiManagerService apiManager = getRetrofit(LoactionUrl.API_SERVER).create(ApiManagerService.class);

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    public static Retrofit getRetrofit(String baseUrl) {

        if (mRetrofit == null) {

            if (mOkHttpClient == null) {
                mOkHttpClient =getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }

    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
//                    .cookieJar(new CookiesManager())
                    //.addInterceptor(new MyIntercepter())
//                    .addNetworkInterceptor(new CacheInterceptor())
                    //设置请求读写的超时时间
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }

    public static  class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //cache for 30 days
//                    .header("Cache-Control", "max-age=" + 3600 * 24 * 30)
                    .header("Cache-Control", "max-age=" + 0)
                    .build();
            return response1;
        }
    }
}
