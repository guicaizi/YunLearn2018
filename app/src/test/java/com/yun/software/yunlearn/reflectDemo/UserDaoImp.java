package com.yun.software.yunlearn.reflectDemo;

/**
 * Created by yanliang
 * on 2018/4/3 11:06
 */

public class UserDaoImp implements UserDao {
    @Override
    public void add() {
        System.out.println("添加功能");
    }

    @Override
    public void remove() {
        System.out.println("删除功能");

    }

    @Override
    public void update() {
        System.out.println("更新功能");

    }

    @Override
    public void requre() {
        System.out.println("查询功能");


    }
}
