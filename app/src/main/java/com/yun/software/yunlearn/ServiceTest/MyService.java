package com.yun.software.yunlearn.ServiceTest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.yun.software.corelib.Tools.MyLogUtils;

import java.util.Random;

/**
 * Created by yanliang
 * on 2018/3/30 11:10
 */

public class MyService extends Service {

    public static final String TAG = "MyService";
    private IBinder myBinder;

    private Random mGenerator;

    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return myBinder;
    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind");
        super.onRebind(intent);
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
 
    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
        mGenerator = new Random();
        Log.i(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand() executed");
        MyLogUtils.i(TAG,"startid---》"+startId);
//        每次回调onStartCommand()方法时，参数“startId”的值都是递增的，startId用于唯一标识每次对Service发起的处理请求
//        如果服务同时处理多个 onStartCommand() 请求，则不应在处理完一个启动请求之后立即销毁服务，
        // 因为此时可能已经收到了新的启动请求，在第一个请求结束时停止服务会导致第二个请求被终止。为了避免这一问题，
        // 可以使用 stopSelf(int) 确保服务停止请求始终基于最新一次的启动请求。也就是说，如果调用 stopSelf(int)
        // 方法的参数值与onStartCommand()接受到的最新的startId值不相符的话，stopSelf()方法就会失效，从而避免终止尚未处理的请求
//        START_NOT_STICKY
//        如果系统在 onStartCommand() 返回后终止服务，则除非有挂起 Intent 要传递，否则系统不会重建服务。这是最安全的选项，
        // 可以避免在不必要时以及应用能够轻松重启所有未完成的作业时运行服务
//                START_STICKY
//        如果系统在 onStartCommand() 返回后终止服务，则会重建服务并调用 onStartCommand()，但不会重新传递最后一个 Intent。相反，
        // 除非有挂起 Intent 要启动服务（在这种情况下，将传递这些 Intent ），否则系统会通过空 Intent 调用 onStartCommand()。这适用于不执行命令、但无限期运行并等待作业的媒体播放器（或类似服务）
//        START_REDELIVER_INTENT
//        如果系统在 onStartCommand() 返回后终止服务，则会重建服务，并通过传递给服务的最后一个 Intent 调用 onStartCommand()。
        // 任何挂起 Intent 均依次传递。这适用于主动执行应该立即恢复的作业（例如下载文件）的服务



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() executed");
    }



}

