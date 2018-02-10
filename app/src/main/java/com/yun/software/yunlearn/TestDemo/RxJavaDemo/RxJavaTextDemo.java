package com.yun.software.yunlearn.TestDemo.RxJavaDemo;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.R.attr.value;

/**
 * Created by yanliang
 * on 2018/1/16 16:38
 */

public class RxJavaTextDemo extends BaseActivity {
    @Bind(R.id.btn_one)
    Button btnOne;
    @Bind(R.id.btn_two)
    Button btnTwo;
    @Bind(R.id.tv_show)
    TextView tvShow;
    @Bind(R.id.btn_one1)
    Button btnOne1;
    @Bind(R.id.btn_one2)
    Button btnOne2;
    @Bind(R.id.btn_one3)
    Button btnOne3;
    private Observable<Integer> observable;
    private Observer<Integer> observer;

    @Override
    public int getLayoutId() {
        return R.layout.rxjava_test;
    }

    @Override
    public void setData() {
        Log.d(TAG, "当前线程名字" + Thread.currentThread().getName());
    }

    @Override
    public void addLisener() {
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreatObservable();
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        Log.d(TAG, "" + value);
                        tvShow.setText(integer + "");
                    }
                });
            }
        });
        btnOne1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                    }
                }).flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
                        final List<String> list = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            list.add("I am value " + integer);
                        }
                        return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d(TAG, "" + s);
//                        tvShow.setText(s);
                    }
                });

            }
        });




    }

    @Override

    public Activity getActivity() {
        return this;
    }

    public void CreatObservable() {
        observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "" + value);
                tvShow.setText(value + "");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };


    }


}
