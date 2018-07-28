package com.yun.software.yunlearn.rxdemo;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yanliang
 * on 2018/4/17 11:43
 */

public class RxManager {


    public static  void  regeistrx(){
        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(final FlowableEmitter<Integer> emitter) throws Exception {
                        System.out.println( "before emit, requested = " + emitter.requested());

                        System.out.println(  "emit 1");
                        emitter.onNext(1);
                        System.out.println(  "after emit 1, requested = " + emitter.requested());

                        System.out.println(  "emit 2");
                        emitter.onNext(2);
                        System.out.println(  "after emit 2, requested = " + emitter.requested());

                        System.out.println(  "emit 3");
                        emitter.onNext(3);
                        System.out.println(  "after emit 3, requested = " + emitter.requested());

                        System.out.println(  "emit complete");
                        emitter.onComplete();

                        System.out.println(  "after emit complete, requested = " + emitter.requested());
                    }
                }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println(  "onSubscribe");
//                        mSubscription = s;
                        s.request(10);  //request 10
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(  "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //                            Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(  "onComplete");
                    }
                });
    }
    public static void request() {
//        mSubscription.request(96); //请求96个事件
    }

    public static void demo4() {
        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                      System.out.println(  "First requested = " + emitter.requested());
                        boolean flag;
                        for (int i = 0; ; i++) {
                            flag = false;
                            while (emitter.requested() == 0) {
                                if (!flag) {
                                  System.out.println(  "Oh no! I can't emit value!");
                                    flag = true;
                                }
                            }
                            emitter.onNext(i);
                          System.out.println(  "emit " + i + " , requested = " + emitter.requested());
                        }
                    }
                }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                      System.out.println(  "onSubscribe");
//                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                      System.out.println(  "onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                      System.out.println(  "onComplete");
                    }
                });
    }

    




}
