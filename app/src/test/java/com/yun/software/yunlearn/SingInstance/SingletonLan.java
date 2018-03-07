package com.yun.software.yunlearn.SingInstance;

/**
 * Created by yanliang
 * on 2018/3/6 15:50
 */

public class SingletonLan {

    private static  SingletonLan ourInstance =null;

    public static SingletonLan getInstance() {
        if(ourInstance==null){
            ourInstance=new SingletonLan();

        }
        return ourInstance;
    }

    private SingletonLan() {
    }
}
