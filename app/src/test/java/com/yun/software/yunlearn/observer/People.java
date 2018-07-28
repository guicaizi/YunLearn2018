package com.yun.software.yunlearn.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yanliang
 * on 2018/6/14 14:40
 */

public class People implements Observer {
    String name;
    String age;

    public People(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name+String.valueOf(arg));

    }
}
