package com.yun.software.yunlearn.ServiceTest;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/5/11 15:13
 */

public class TestBingService extends BaseActivity {
    @Bind(R.id.btn_bind)
    Button btnBind;
    @Bind(R.id.btn_start_service)
    Button btnstart;
    @Bind(R.id.btn_unbind_service)
    Button btnUnbindService;
    @Bind(R.id.btn_stop_service)
    Button btnStopService;
    @Bind(R.id.btn_test)
    Button btnTest;
    @Bind(R.id.btn_go2)
    Button btngo2;
    UpLoadVidoeService.MyBinder myBinder;
    UpLoadVidoeService testBingService;
    MyConn myConn;
    private Intent service;
    private boolean isintercept=false;
    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_servce;
    }

    @Override
    public void setData() {
        service = new Intent(this , UpLoadVidoeService.class);
        myConn=new MyConn();
    }

    @Override
    public void addLisener() {
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(service);
            }
        });
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 bindserviceM();
            }
        });
        btnUnbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    unbindserviceM();
            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(service);
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  testBingService.startDownLoad(new UploadDateListener() {
                      @Override
                      public void update(final int progress) {
                          runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  if(!isintercept)
                                   btnBind.setText(progress+"");
                              }
                          });
                      }
                  });
            }
        });
        btngo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterPage(SecondTest.class);
            }
        });

    }

    public void bindserviceM(){


         bindService(service,myConn,  BIND_AUTO_CREATE);



    }
    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取到服务内部返回的代理对象 ，用binder承接起来
           myBinder= (UpLoadVidoeService.MyBinder) service;
           testBingService=myBinder.getService();
            Log.i("upload服务","服务连接成功");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("upload服务","服务连接失败");
        }
    }

    public void unbindserviceM(){
        unbindService(myConn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isintercept=true;
//        unbindserviceM();
    }

    @Override
    public Activity getActivity() {
        return this;
    }


}
