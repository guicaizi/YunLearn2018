package com.yun.software.yunlearn.app;

import com.yun.software.corelib.base.BaseApplication;

/**
 * Created by yanliang
 * on 2017/11/29 18:08
 */

public class AppApplication extends BaseApplication {

    @Override
    protected void onConfig() {
        setIsWatcherRef(false);
    }

    @Override
    protected void onRelease() {

    }

    @Override
    protected BaseApplication getChildInstance() {
        return this;
    }
}
