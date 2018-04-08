package com.yun.software.yunlearn.DaggerDemo;

import javax.inject.Inject;

/**
 * Created by yanliang
 * on 2018/3/7 15:32
 */

public class Person {
     Food food;
     @Inject
     public Person(Food food) {
        this.food = food;
    }
     public String eating(){
       return food.eating();
     }

}
