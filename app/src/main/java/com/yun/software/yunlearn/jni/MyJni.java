package com.yun.software.yunlearn.jni;

/**
 * Created by yanliang
 * on 2018/4/9 17:02
 */

public class MyJni {

    public native  int testAdd(int a,int b);
    public native String getHelloWord(String args);
    static {

        System.loadLibrary("MyJni");


    }
}
