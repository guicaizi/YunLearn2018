package com.yun.software.yunlearn.ServiceTest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2018/5/11 17:40
 */

public class SecondTest extends BaseActivity {
    @Bind(R.id.btn_bind_service)
    Button btnBindService;
    UpLoadVidoeService.MyBinder myBinder;
    UpLoadVidoeService testBingService;
    MyConn myConn;
    private Intent service;
    private boolean isintercept=false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_second_service;
    }

    @Override
    public void setData() {
        service = new Intent(this , UpLoadVidoeService.class);
        myConn=new MyConn();
    }

    @Override
    public void addLisener() {
        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(service);
                bindService(service,myConn,  BIND_AUTO_CREATE);
            }
        });

    }

    public void getdate(){
        testBingService.startDownLoad(new UploadDateListener() {
            @Override
            public void update(final int progress) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!isintercept){
                            btnBindService.setText(progress+"");
                        }

                    }
                });
            }
        });
    }
    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取到服务内部返回的代理对象 ，用binder承接起来
            myBinder= (UpLoadVidoeService.MyBinder) service;
            testBingService=myBinder.getService();
            getdate();
            Log.i("upload服务","服务连接成功");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("upload服务","服务连接失败");
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isintercept=true;
        unbindService(myConn);
    }
}
