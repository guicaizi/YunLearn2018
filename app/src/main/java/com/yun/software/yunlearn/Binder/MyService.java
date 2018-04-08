package com.yun.software.yunlearn.Binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.yun.software.yunlearn.Myapp;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        Myapp myapp=
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
