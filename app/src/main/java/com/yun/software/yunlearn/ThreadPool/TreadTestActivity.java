package com.yun.software.yunlearn.ThreadPool;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/2/10 17:12
 */

public class TreadTestActivity extends BaseActivity {
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;
    ThreadManager threadManager ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_thread_pools_test;
    }

    @Override
    public void setData() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("这里是Title");
//        toolbar.setSubtitle("这里是子标题");
//        toolbar.setLogo(R.drawable.icon);
//        setSupportActionBar(toolbar);
        threadManager=ThreadManager.getInstance(this);

    }

    @Override
    public void addLisener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadManager.excudeNewCachedTheadPool();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadManager.excudeNewFixedThreadPool();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadManager.excudeNewScheduledThreadPool();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadManager.excudeNewSingleThreadExecutor();
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

}
