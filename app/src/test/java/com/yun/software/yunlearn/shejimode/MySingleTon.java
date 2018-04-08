package com.yun.software.yunlearn.shejimode;

/**
 * Created by yanliang
 * on 2018/4/4 10:10
 */

public class MySingleTon {
    private static final MySingleTon ourInstance = new MySingleTon();

    public static MySingleTon getInstance() {
        return ourInstance;
    }

    private MySingleTon() {
    }
}
