package com.yun.software.yunlearn.abstractfactory;

/**
 * Created by 48608 on 2018/2/28.
 */

public class IOSFactory implements IFactory{
    @Override
    public IApi create(int type) {
        return new IOSApi();
    }
}









