package com.yun.software.yunlearn.rxdemo;



import android.util.Log;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by yanliang
 * on 2018/4/16 14:41
 */

public class RxTest {
    @Test
    public void test01(){
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("河南");
//                Log.i("rxtest","河南");
                Thread.sleep(1000);
                e.onNext("光山");
                Thread.sleep(1000);
                e.onNext("晏河");
                Thread.sleep(1000);
                System.out.println( "Observer thread is :" + Thread.currentThread().getName());
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                 if((s.equals("河南"))){
                     return 1;
                 }else{
                     return (s.equals("光山"))?2:3;
                 }

            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {

                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }

                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        });

        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String integer) {
                System.out.println(integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
    @Test
    public void test02(){
          RxManager.regeistrx();

        }


    @Test
    public void test03(){
        boolean flag=true;
        boolean flag2=true;
        int i=0;
        while (flag) {
            while (flag2) {
                if (i==10) {
                    break;
                }
                i++;
                System.out.println("hello"+i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello");

        }




    }






        
    




}
