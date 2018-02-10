package com.yun.software.corelib.UrlRequest;


import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yanliang
 * on 2017.07.15:47
 */
public class Api {
    /**
     *请求超时时长
     */  
    public static final int READ_TIME_OUT = 3000;
    public static final int CONNECT_TIME_OUT = 3000;
    public Retrofit retrofit;
    public OkHttpClient okHttpClient;
    public TrainService trainService;
    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    private Api(int hostType) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json;charset:utf-8")
                        .addHeader("Authorization", ApiConstants.Authorization)
                        .addHeader("AppId", ApiConstants.appid)
                        .build();
                return chain.proceed(build);
            }
        };
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(new LogInterceptor())
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.getHost(hostType))
                .build();
        trainService = retrofit.create(TrainService.class);
    }

    /**
     * Params hosttype 服务端口号类型
     * Auther yanliang
     * return 获取公共请求处理方法
     */
    public static TrainService getTrainService(int hostType) {
        Api retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new Api(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.trainService;
    }
}