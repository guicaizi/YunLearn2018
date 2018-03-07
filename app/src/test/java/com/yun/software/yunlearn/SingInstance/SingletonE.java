package com.yun.software.yunlearn.SingInstance;

/**
 * Created by yanliang
 * on 2018/3/6 15:50
 */

public class SingletonE {
    private static final SingletonE ourInstance = new SingletonE();

    public static SingletonE getInstance() {
        return ourInstance;
    }

    private SingletonE() {
    }
}
