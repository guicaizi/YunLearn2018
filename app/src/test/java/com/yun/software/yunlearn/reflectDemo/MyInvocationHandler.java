package com.yun.software.yunlearn.reflectDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yanliang
 * on 2018/4/3 11:09
 */

public class MyInvocationHandler implements InvocationHandler {

    private Object target; // 目标对象

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("检查登录");
        Object obj=method.invoke(target,args);
        System.out.println("查询完毕");
        return obj;
    }
}
