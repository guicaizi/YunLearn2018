package com.yun.software.yunlearn.DaggerDemo;

import android.app.Activity;
import android.widget.TextView;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/3/7 15:22
 */

public class Dagger2TestDemo extends BaseActivity {
    @Bind(R.id.tv_dagger2)
    TextView tvDagger2;
    @Inject
    Person person;

    @Override
    public int getLayoutId() {
        return R.layout.test_dagger2_test;
    }

    @Override
    public void setData() {
     DaggerFoodComponent.builder()
                 .build().inject(this);
     tvDagger2.setText(person.eating());


    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }


}
