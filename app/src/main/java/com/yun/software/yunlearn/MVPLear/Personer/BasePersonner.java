package com.yun.software.yunlearn.MVPLear.Personer;

import com.yun.software.yunlearn.MVPLear.Modle.BaseLoding;
import com.yun.software.yunlearn.MVPLear.Modle.MyLoding;
import com.yun.software.yunlearn.MVPLear.View.IyunLearnView;

import java.lang.ref.WeakReference;

/**
 * Created by yanliang
 * on 2018/2/7 11:44
 */

public abstract  class BasePersonner<T> {
    WeakReference<T> mViewRefrence;
    IyunLearnView mview;

    BaseLoding loding= new MyLoding();

    /**
     *添加
     */
    public void attachView(T view){
        mViewRefrence=new WeakReference<T>(view);
    }
    /**
     *解绑
     */
    public void unattachView(){
        mViewRefrence.clear();
    }
    public abstract void dateLoding();
}
