package com.yun.software.yunlearn.MVPLear;

import android.app.Activity;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.MVPLear.Personer.MyViewPersoner;
import com.yun.software.yunlearn.MVPLear.View.IyunLearnView;

import java.util.List;

/**
 * Created by yanliang
 * on 2018/2/7 11:03
 */

public class UIControl  extends BaseActivity implements IyunLearnView {

    MyViewPersoner personer;
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void setData() {

        personer=new MyViewPersoner();
        personer.attachView(this);




    }

    @Override
    public void addLisener() {

    }
    public void loadDate(){
        personer.dateLoding();
    }
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        personer.unattachView();
    }

    @Override
    public void showLoding() {

    }

    @Override
    public void showDate(List<String> strings) {

    }
}
