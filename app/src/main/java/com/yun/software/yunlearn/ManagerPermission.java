package com.yun.software.yunlearn;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yun.software.corelib.Tools.TopBarUtil;
import com.yun.software.corelib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2017/12/1 16:10
 */

public class ManagerPermission extends BaseActivity {
    @Bind(R.id.tv_base_back)
    TextView tvBaseBack;
    @Bind(R.id.tv_base_title)
    TextView tvBaseTitle;
    @Bind(R.id.tv_base_goto)
    TextView tvBaseGoto;
    @Bind(R.id.ll_base_topbar)
    LinearLayout llBaseTopbar;
    @Bind(R.id.v_base_line)
    View vBaseLine;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    private int progress=0;
    private Notification.Builder builder;
    private NotificationManager notificationManager;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            builder.setProgress(100,progress, false)
                    .setContentText("下载进度:" + progress + "%");
            notificationManager.notify(1115, builder.build());
            if(progress==100){
                notificationManager.cancel(1115);
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_permission;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setData() {
        TopBarUtil.initBtnBack(this, R.id.tv_base_back);
        TopBarUtil.initTitle(this, R.id.tv_base_title, "权限申请");
        initnotifationbar();


    }
    @Override
    public void addLisener() {
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                List<String> mList = null;
                if ((mList = checkNeedPermission()).isEmpty()) {
                    showShortToast("权限申请");
                } else {
                    requestPermissions(MyPermissions.STORAGE, 1);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress<100){
                            progress++;
                            try {
                                Thread.sleep(200);
                                handler.sendEmptyMessage(1);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

            }
        });

    }
    private List<String> checkNeedPermission() {

        List<String> deniedPermissions = new ArrayList<>();

        for (int i = 0; i < MyPermissions.STORAGE.length; i++) {

            if (ContextCompat.checkSelfPermission(mContext, MyPermissions.STORAGE[i]) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(MyPermissions.STORAGE[i]);
            }
        }
        return deniedPermissions;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (checkNeedPermission().isEmpty()) {
            showShortToast("权限通过");
        } else {
            showShortToast("申请权限失败");
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void  initnotifationbar(){
        builder = notificationInit(mContext);
        notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);


    }


    private static Notification.Builder notificationInit(Context context) {
        Intent intent = new Intent(context, context.getClass());
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.stat_sys_download)
                .setTicker("开始下载")
                .setContentTitle("学习")
                .setContentText("正在更新")
                .setContentIntent(pIntent)
                .setWhen(System.currentTimeMillis());
        return builder;
    }




}
