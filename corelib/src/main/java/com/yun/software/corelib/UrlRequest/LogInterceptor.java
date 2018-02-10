package com.yun.software.corelib.UrlRequest;

import android.util.Log;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by yanliang
 * 2017/1/3
 */
public class LogInterceptor implements Interceptor {
    public static String TAG = "http";
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.i(TAG,"\n");
        Log.i(TAG,"----------Start----------------");
        Log.i(TAG, "| "+request.toString());
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + URLEncoder.encode(body.encodedValue(i),"utf-8") + "&");
                }
                if(sb.length()>0){
                    sb.delete(sb.length() - 1, sb.length());
                    Log.i(TAG, "post请求转get地址：---->"+request.url()+"?"+sb.toString());
                }
            }
        }
        Log.i(TAG,"----------End:"+duration+"毫秒----------");
        Log.i(TAG, "| Response:" + content);


        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
