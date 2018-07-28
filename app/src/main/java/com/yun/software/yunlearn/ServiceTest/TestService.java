package com.yun.software.yunlearn.ServiceTest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/3/30 10:49
 */

public class TestService extends BaseActivity {
    @Bind(R.id.btn_start_service)
    Button btnStartService;
    @Bind(R.id.btn_bind_service)
    Button btnBindService;
    @Bind(R.id.btn_unbind_service)
    Button btnUnbindService;
    @Bind(R.id.btn_stop_service)
    Button btnStopService;

    private MyService mService;

    private boolean mBound = false;

    @Override
    public int getLayoutId() {
        return R.layout.test_service;
    }

    @Override
    public void setData() {

    }

    @Override
    public void addLisener() {
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(mContext, MyService.class);
                startService(startIntent);
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent = new Intent(mContext, MyService.class);
                stopService(stopIntent);
            }
        });
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });
        btnUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mBound) {
                    unbindService(mConnection);
//                    mBound = false;
//                }
            }
        });

    }
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService.MyBinder binder = (MyService.MyBinder) service;
//            mService = binder.getService();
//            Toast.makeText(mContext, "绑定成功", Toast.LENGTH_SHORT).show();
//            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            Toast.makeText(mContext, "绑定是啊比", Toast.LENGTH_SHORT).show();
//            mBound = false;
        }
    };

    public void getData(View view) {
//        if (mBound) {
//            Toast.makeText(this, "获取到的随机数：" + mService.getRandomNumber(), Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "服务未绑定", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mBound) {
//            unbindService(mConnection);
//            mBound = false;
//        }
    }


    @Override
    public Activity getActivity() {
        return this;
    }

}
