package com.yun.software.yunlearn.shejimode;

/**
 * Created by yanliang
 * on 2018/4/4 10:10
 */

public class Test {
    @org.junit.Test
    public void testT(){


    }
    static MyCreatT people=new MyCreatT<People>(){

        @Override
        protected People create() {
            return null;
        }
    };

}
