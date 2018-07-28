package com.yun.software.yunlearn.Binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.yun.software.yunlearn.Myapp;

public class MyService extends Service {


    class myAppImp  extends Myapp.Stub{
        String name="橘子";
        @Override
        public String getName() throws RemoteException {
            return name;
        }

        @Override
        public String setName(String str) throws RemoteException {
            return name=str;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        Myapp myapp=
        return new myAppImp();
    }
}
