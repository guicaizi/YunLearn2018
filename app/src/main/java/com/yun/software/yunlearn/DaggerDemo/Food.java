package com.yun.software.yunlearn.DaggerDemo;

import javax.inject.Inject;

/**
 * Created by yanliang
 * on 2018/3/7 15:31
 */

public class Food {
    @Inject
    public Food() {
    }

    public String eating(){
        return "大苹果";

    }




}
