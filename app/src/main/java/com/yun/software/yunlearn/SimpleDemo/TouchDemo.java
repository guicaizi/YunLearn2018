package com.yun.software.yunlearn.SimpleDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.yun.software.corelib.Tools.MyLogUtils;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;
import com.yun.software.yunlearn.Tools.MaxScollMonitorView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2018/4/24 14:05
 */

public class TouchDemo extends BaseActivity {
    @Bind(R.id.maxscoll)
    MaxScollMonitorView maxscoll;

    @Override
    public int getLayoutId() {
        return R.layout.activity_touch_demo;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setData() {
        maxscoll.setListener(new MaxScollMonitorView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {


                MyLogUtils.i("ontouch","滚动Y距离"+scrollY);


            }
        });

    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }


}
