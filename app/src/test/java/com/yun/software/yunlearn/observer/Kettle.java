package com.yun.software.yunlearn.observer;


import com.yun.software.yunlearn.shejimode.MySingleTon;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yanliang
 * on 2018/6/14 14:28
 */

public class Kettle<T> extends Observable {


    private Kettle() {

    }
    public void getInstance(){

    }
    public static class KettleHolder{
        static Kettle  single=new Kettle();
        public static Kettle getInstance(){
            return single;
        }
    }

    /**
     * 发布信息
     */
    public void publishEvent(T t){
        System.out.println(t);

        if (countObservers()== 0)
            throw new NullPointerException("you must regist Observer first!");
        notifyData(t);
    }

    /**
     * 通知订阅者
     */
    public void notifyData(T t){
        setChanged();
        notifyObservers(t);

    }

    /**
     * 注册一个观察者
     */
    public void registObserver(Observer o){
        addObserver(o);
    }

    /**
     * 在你需要的时候调用这个方法，防止内存泄露
     */
    public void unregistObserver(Observer o){
         deleteObserver(o);
    }
}


