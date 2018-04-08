package com.yun.software.yunlearn.reflectDemo;

/**
 * Created by yanliang
 * on 2018/4/3 10:42
 */

public class People {
    private String name;
    private String age;

    public People(String name) {
        this.name = name;
    }

    private People(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public People() {
    }

    public  void showInfor(){

        System.out.println("姓名"+name+"年龄："+age);



    }
    private   void showInforage(){

        System.out.println("年龄："+age);



    }








}
