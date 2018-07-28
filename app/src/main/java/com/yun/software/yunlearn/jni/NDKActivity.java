package com.yun.software.yunlearn.jni;

import android.app.Activity;
import android.widget.TextView;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.DaggerDemo.DaggerFoodComponent;
import com.yun.software.yunlearn.DaggerDemo.Person;
import com.yun.software.yunlearn.R;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/4/9 18:02
 */

public class NDKActivity extends BaseActivity {

   @Bind(R.id.tv_show)
   TextView tvShow;
    @Override
    public int getLayoutId() {
        return R.layout.test_ndk_test;
    }

    @Override
    public void setData() {
      MyJni jin=new MyJni();
      int b=jin.testAdd(5,9);
      tvShow.setText("5+9的和为："+b);


    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }



}
