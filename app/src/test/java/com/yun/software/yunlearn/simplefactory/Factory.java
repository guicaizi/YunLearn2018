package com.yun.software.yunlearn.simplefactory;

/**
 * Created by 48608 on 2018/2/28.
 */

public class Factory {
    public static Api create(int type){
        switch (type){
            case 1:
                return new ImplA();
            case 2:
                return new ImplB();
            case 3:
                return new ImplC();
            default:
                return new ImplC();
        }
    }
    public static <T extends Api> T createProduct(Class<T> clz){
        Api api=null;
        try {
            api=(Api)Class.forName(clz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T)api;
    }
}



