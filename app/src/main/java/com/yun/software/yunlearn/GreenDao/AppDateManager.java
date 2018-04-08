package com.yun.software.yunlearn.GreenDao;

/**
 * Created by yanliang
 * on 2018/3/9 15:24
 */

public class AppDateManager {
    private static YunCompanyDao categoryDao;
    private static AppDateManager appDataManager;
    private AppDateManager(DaoSession daoSession) {

        categoryDao = daoSession.getYunCompanyDao();}

    public static void init(DaoSession daoSession) {
        if (appDataManager == null) {
            synchronized (AppDateManager.class) {
                appDataManager = new AppDateManager(daoSession);
            }
        }
    }

}
