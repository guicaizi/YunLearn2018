package com.yun.software.corelib.UrlRequest;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by yanliang
 * on 2017/11/24 11:48
 */

public interface TrainService {
    /**
     * 积分统计
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/sys/userScore/calculation")
    Call<ResponseBody> getScoreCaculation(@Body RequestBody route);
    /**
     * 积分查询
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/sys/scoreFlow/getCurrent")
    Call<ResponseBody> getAllScore(@Body RequestBody route);
    /**
     * 同步考试成绩
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/study/synchronization/exam")
    Call<ResponseBody> getSynchronizedExam(@Body RequestBody route);
    /**
     * 同步培训成绩
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/study/synchronization/training ")
    Call<ResponseBody> getSynchronizedTraining(@Body RequestBody route);
}
