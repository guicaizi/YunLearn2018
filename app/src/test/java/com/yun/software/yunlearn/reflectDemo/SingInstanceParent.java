package com.yun.software.yunlearn.reflectDemo;

/**
 * Created by yanliang
 * on 2018/4/3 11:19
 */

public class SingInstanceParent {
    private static SingInstanc instanc;

    public SingInstanceParent() {
        instanc=new SingInstanc("daylan");
    }
}
