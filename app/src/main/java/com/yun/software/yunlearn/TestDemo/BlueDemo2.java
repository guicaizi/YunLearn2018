package com.yun.software.yunlearn.TestDemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yun.software.corelib.Tools.TopBarUtil;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2017/12/26 11:33
 */

public class BlueDemo2 extends BaseActivity {
    private static final int REQUEST_ACCESS_COARSE_LOCATION = 1;
    @Bind(R.id.btn_blue)
    Button btnblue;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;

    private static final int REQUEST_ENABLE_BT = 1;
    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_blue_demo;
    }

    @SuppressLint("NewApi")
    @Override
    public void setData() {
        TopBarUtil.initBtnBack(this, R.id.tv_base_back);
        TopBarUtil.initTitle(this, R.id.tv_base_title, "权限申请");
//        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
//            //            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
//            finish();
//        }

        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
//        // BluetoothAdapter through BluetoothManager.
        Log.i("http","蓝牙设备");
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if(Build.VERSION.SDK_INT>=23){
            //判断是否有权限
            if (ContextCompat.checkSelfPermission(BlueDemo2.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_ACCESS_COARSE_LOCATION);
                //向用户解释，为什么要申请该权限
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    Toast.makeText(BlueDemo2.this,"shouldShowRequestPermissionRationale", Toast.LENGTH_SHORT).show();
                }
            }
        }
//        // Checks if Bluetooth is supported on the device.
//        if (mBluetoothAdapter == null) {
//            //            Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
//            finish();
//            return;
//        }



    }

    @Override
    public void addLisener() {
        btnblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("http","蓝牙设备");
                scanLeDevice(true);
            }
        });

    }

    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(mScanning)
                    {
                        mScanning = false;
                        mBluetoothAdapter.stopLeScan(mLeScanCallback);
                        invalidateOptionsMenu();
                    }
                }
            }, SCAN_PERIOD);
            mScanning = true;
            //F000E0FF-0451-4000-B000-000000000000
//            mLeDeviceListAdapter.clear();
            mHandler.sendEmptyMessage(1);
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        invalidateOptionsMenu();
    }
    public final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: // Notify change
                    //                    mLeDeviceListAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mHandler.sendEmptyMessage(1);
                            Log.i("http","找到蓝牙设备");
                        }
                    });
                }
            };
    @Override
    public Activity getActivity() {
        return this;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        if (requestCode == REQUEST_ACCESS_COARSE_LOCATION) {
            if (permissions[0] .equals(Manifest.permission.ACCESS_COARSE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scanLeDevice(true);
                // 用户同意使用该权限
            } else {
                // 用户不同意，向用户展示该权限作用
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    showShortToast("用来扫描附件蓝牙设备的权限，请手动开启！");
                    return;
                }
            }
        }
    }
}
