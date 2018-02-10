package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.os.Message;
import android.widget.TextView;

import com.yun.software.corelib.Tools.DateTimeUtil;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yanliang
 * on 2018/1/3 10:00
 */

public class ScheduledExecutorServiceTest extends BaseActivity {
    @Bind(R.id.tv_show)
    TextView tvShow;
    long starttime;

    @Override
    public int getLayoutId() {
        return R.layout.scheduled_test;
    }

    @Override
    public void setData() {

    }

    @Override
    public void addLisener() {

    }

    @OnClick(R.id.btn_start)
    public void start() {
        starttime=System.currentTimeMillis();
        executeFixedRate();


    }

    @Override
    public Activity getActivity() {
        return this;
    }


    /**
     * 以固定周期频率执行任务
     */
    public void executeFixedRate() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(
                new EchoServer(),
                10000,
                1000,
                TimeUnit.MILLISECONDS);
    }

    public void executeEightAtNightPerDay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay = DateTimeUtil.getTimeMillis("20:00:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

        executor.scheduleAtFixedRate(
                new EchoServer(),
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }

    class EchoServer implements Runnable {
        @Override
        public void run() {
            sendEmptyUiMessage(1);
            //            System.out.println("This is a echo server. The current time is " +
            //                    System.currentTimeMillis() + ".");
        }
    }

    @Override
    public void handleUiMessage(Message msg) {
        super.handleUiMessage(msg);

        tvShow.setText("开始时间"+(System.currentTimeMillis()-starttime)+"毫秒");

    }
}
