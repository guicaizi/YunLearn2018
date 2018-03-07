package com.yun.software.yunlearn.abstractfactory;

import org.junit.Test;

/**
 * Created by 48608 on 2018/2/28.
 */

public class client {
    @Test
    public void test(){
        int i=0;
        if(i==1) {
            new IOSFactory().create(1).show();
//            MediaPlayer m = new MediaPlayer();
        }else if(i==2){
            new AndroidFactory().create(2).show();
        }
    }
}









