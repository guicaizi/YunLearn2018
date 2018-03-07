package com.yun.software.yunlearn.abstractfactory;

/**
 * Created by 48608 on 2018/2/28.
 */

public class AndroidFactory implements IFactory{
    @Override
    public IApi create(int type) {
        if(type==1) {
            return new AndroidApi();
        }
        return null;
    }
}









