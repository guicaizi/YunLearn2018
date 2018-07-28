package com.yun.software.yunlearn.SimpleDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yun.software.corelib.Tools.MyLogUtils;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;
import com.yun.software.yunlearn.Widget.CoustomTestView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2018/5/3 16:36
 */

public class CoustomView extends BaseActivity {
    @Bind(R.id.cv)
    CoustomTestView cv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_coustomview;
    }

    @Override
    public void setData() {

    }

    @Override
    public void addLisener() {
        cv.setOnClickListener(new CoustomTestView.OnClickListener() {
            @Override
            public void onClick(CoustomTestView view) {
                MyLogUtils.i("coustomTestView","点击我了");
            }

            @Override
            public void onLongClick(CoustomTestView view) {
                MyLogUtils.i("coustomTestView","长按我了");

            }
        });

    }

    @Override
    public Activity getActivity() {
        return this;
    }


}
