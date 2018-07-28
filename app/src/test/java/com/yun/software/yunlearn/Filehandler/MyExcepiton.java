package com.yun.software.yunlearn.Filehandler;

/**
 * Created by yanliang
 * on 2018/4/13 16:03
 */

public class MyExcepiton extends Exception {
    String message;

    public MyExcepiton(String message) {
        super(message);
        this.message = message;
    }
}
