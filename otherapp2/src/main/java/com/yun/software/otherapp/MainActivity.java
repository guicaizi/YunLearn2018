package com.yun.software.otherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yun.software.yunlearn.Myapp;

public class MainActivity extends AppCompatActivity {
     public static final  String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindServiceOne(View view){
        Intent intent=new Intent();
        intent.setAction("com.yun.software.yunlearn.Binder.MyService");
        intent.setPackage("com.yun.software.yunlearn");
        bindService(intent,myConnectService, Context.BIND_AUTO_CREATE);
        Log.i(TAG,"运行了");

    }
    private ServiceConnection myConnectService=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(TAG,"绑定成功");
            Myapp myapp=Myapp.Stub.asInterface(service);
            try {
//                Toast.makeText(MainActivity.this,myapp.getName(),Toast.LENGTH_SHORT);
                Log.i(TAG,"绑定成功"+myapp.getName());
                myapp.setName("苹果");
                Log.i(TAG,"绑定成功"+myapp.getName());
//                Toast.makeText(MainActivity.this,myapp.getName(),Toast.LENGTH_SHORT);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"绑定失败");

        }
    };
}
