package com.yun.software.yunlearn.ServiceTest;

import android.app.Service;
import android.content.Intent;
import android.icu.util.IslamicCalendar;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;



/**
 * Created by yanliang
 * on 2018/5/11 14:41
 */

public class UpLoadVidoeService extends Service  {

    private String teststr;
    private UpLoadVidoeService muploadservice;
    private boolean isStart=false;
    private  volatile  boolean  intercept=false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("upload服务","服务绑定了");
        teststr="我是测试服务对象获取情况";
        return new MyBinder();
    }

    public UpLoadVidoeService getUploadVidoeService() {
        return muploadservice=this;
    }

    class MyBinder extends Binder {

        public UpLoadVidoeService getService() {
            return getUploadVidoeService();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("upload服务","服开启了");
        return super.onStartCommand(intent, flags, startId);

    }

    public void TestMian(){


       Log.i("upload服务",teststr);


    }
    private UploadDateListener updateListener;

    public void updateProgress(int progress){
        updateListener.update(progress);


    }
    public UploadDateListener getUpdateListener(){

        return updateListener;

    }
    int i=0;
    public void startDownLoad( UploadDateListener updateListener){
        this.updateListener=updateListener;
        intercept=false;
        if(!isStart){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isStart=true;
                    while (!intercept){
                        try {
                            i++;
                            updateProgress(i);
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                    Log.i("upload服务","当前线程终止了");
                }
            }).start();
        }
    }
    // 开启服务后 服务职能绑定一次 和解绑一次 多次绑定没反应

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("upload服务","服务解绑了");
        intercept=true;
        isStart=false;

//        muploadservice=null;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {

        Log.i("upload服务","服务销毁了");
        super.onDestroy();
    }
}
