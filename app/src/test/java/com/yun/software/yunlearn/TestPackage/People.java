package com.yun.software.yunlearn.TestPackage;

/**
 * Created by yanliang
 * on 2018/3/1 13:15
 */

public class People {
    int age;

    public People(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void say(){

        System.out.println("我今年"+age);

    }
}
