package com.yun.software.yunlearn;

import android.app.Activity;

import com.yun.software.corelib.Tools.TopBarUtil;
import com.yun.software.corelib.base.BaseActivity;

/**
 * Created by yanliang
 * on 2017/12/1 15:41
 */

public class DownLoadActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    public void setData() {
        TopBarUtil.initBtnBack(this, R.id.tv_base_back);
        TopBarUtil.initTitle(this, R.id.tv_base_title, "下载");
    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
