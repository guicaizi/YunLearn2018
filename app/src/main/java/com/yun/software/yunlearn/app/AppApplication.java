package com.yun.software.yunlearn.app;

import android.content.Context;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.squareup.leakcanary.LeakCanary;
import com.yun.software.corelib.base.BaseApplication;
import com.yun.software.yunlearn.Contents.Constants;
import com.yun.software.yunlearn.Contents.Keys;
import com.yun.software.yunlearn.EventBus.LowMemoryEvent;
import com.yun.software.yunlearn.GreenDao.AppDateManager;
import com.yun.software.yunlearn.GreenDao.DaoMaster;
import com.yun.software.yunlearn.GreenDao.DaoSession;
import com.yun.software.yunlearn.GreenDao.MySQLiteOpenHelper;
import com.yun.software.yunlearn.Hook.HookUtil;
import com.yun.software.yunlearn.Tools.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.database.Database;

/**
 * Created by yanliang
 * on 2017/11/29 18:08
 */

public class AppApplication extends BaseApplication {
     public static final  String TAG="AppApplication";
    @Override
    protected void onConfig() {
        // 关闭内存泄漏查看
//        HookUtil hookUtil=new HookUtil();
//        hookUtil.hookStartActivity(getAppContext());
        setIsWatcherRef(false);
        initGreenDao3(this);
        initLeakCanry();

    }

    @Override
    protected void onRelease() {

    }

    @Override
    protected BaseApplication getChildInstance() {
        return this;
    }
    /**
     * 初始化greenDao3库
     */
    private void initGreenDao3(Context context) {
        //如果你想查看日志信息，请将DEBUG设置为true
        MigrationHelper.DEBUG = true;
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, Constants.DB_NAME, null);
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        AppDateManager.init(daoSession);
    }
    /**
     * 初始化内存分析工具
     */
    private void initLeakCanry() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        boolean canReleaseMemory = (boolean) SPUtils.get(this, Keys.KEY_SP_FORBIDDEN_AUTO_RELEASE_MEMORY_WHEN_LOW_MEMORY, false);
        if (!canReleaseMemory) {
            EventBus.getDefault().post(new LowMemoryEvent(TAG));
        }
    }

}
