package com.yun.software.yunlearn.Hook;

import android.app.Activity;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

/**
 * Created by yanliang
 * on 2018/4/4 10:57
 */

public class HookActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.hookactivity;
    }

    @Override
    public void setData() {

    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
