package com.yun.software.yunlearn.observer;

import java.util.Observable;

/**
 * Created by yanliang
 * on 2018/6/14 15:05
 */

public abstract class LoginOutObservable extends Observable {
    private String userName = "admin";

    /**
     * 设置是否登出，如果是，则需要通知观察者
     *
     * @param isLoginOut isLoginOut
     */
    public void setLoginOut(boolean isLoginOut) {
        if (isLoginOut) {
            String data = "被观察者告诉你：你已经退出登录，请重新登录！";
            /* 注意注意注意：下面这两个方法是要同时调用的，否则不会通知观察者数据发生了变化 */
            setChanged();
            this.notifyObservers(data);
            onUserLoginOut("这是在被观察者内部调用的方法：" + data);
        }
    }

    /**
     * 设置用户名，如果用户名和上次的不一样，则需要通知观察者
     *
     * @param name name
     */
    public void setUserName(String name) {
        if (!name.equals(userName)) {
            this.userName = name;
            String data = "被观察者告诉你：用户名已经改变了！新的名字为：" + name;
            /* 注意注意注意：下面这两个方法是要同时调用的，否则不会通知观察者数据发生了变化 */
            setChanged();
            this.notifyObservers(data);
            onUserNameChanged("这是在被观察者内部调用的方法：" + data);
        }
    }

    /**
     * 当用户名字发生改变时，用于在本类中将数据打印出来
     * <p>
     * 感悟：
     * 抽象方法，凡是继承此类的子类，都需要重写此方法
     * 但是我们可以再本类里调用此方法，然后实例化此类的时候，要求实现类实现本类里头的抽象方法
     *
     * @param newUserName
     */
    public abstract void onUserNameChanged(String newUserName);

    /**
     * 当用户登出时，在本类中用于将数据打印出来
     * <p>
     * 感悟：
     * 抽象方法，凡是继承此类的子类，都需要重写此方法
     * 但是我们可以再本类里调用此方法，然后实例化此类的时候，要求实现类实现本类里头的抽象方法
     *
     * @param message message
     */
    public abstract void onUserLoginOut(String message);
}