package com.yun.software.yunlearn.SingInstance;

/**
 * Created by yanliang
 * on 2018/3/6 15:48
 */

public class Singleton {
    private static volatile Singleton ourInstance=null;

    public static Singleton getInstance() {

        if(ourInstance==null){
            synchronized (Singleton.class){

                if(ourInstance==null){
                    ourInstance=new Singleton();
                }

            }
        }
        return ourInstance;
    }

    private Singleton() {
    }
}
