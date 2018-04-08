package com.yun.software.yunlearn.reflectDemo;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * Created by yanliang
 * on 2018/4/3 11:12
 */

public class InvocationTest {
    @Test
    public void testinvocation(){

        UserDao userDao=new UserDaoImp();

      MyInvocationHandler myInvocationHandler=new MyInvocationHandler(userDao);
      UserDao userDao1= (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),userDao.getClass().getInterfaces(),myInvocationHandler);
      userDao1.add();


    }


}
