package com.yun.software.yunlearn.shejimode;

/**
 * Created by yanliang
 * on 2018/4/4 10:10
 */

public abstract class MyCreatT<T> {
    private T mInstance;

    protected abstract T create();

    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
