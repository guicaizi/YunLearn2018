package com.yun.software.yunlearn.export;

/**
 * Created by 48608 on 2018/2/28.
 */

public abstract class ExportOperator {

    //工厂方法：核心就是实例延迟到了子类去实现
    public abstract ExportFileApi factoryMethod();



    public boolean export(String data) {
        ExportFileApi api=factoryMethod();
//        api.p();
        return api.export(data);
    }
}
