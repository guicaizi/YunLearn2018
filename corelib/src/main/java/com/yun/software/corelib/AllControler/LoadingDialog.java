package com.yun.software.corelib.AllControler;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;

import com.yun.software.corelib.widget.XLoadingDialog;


/**
 * description:弹窗浮动加载进度条
 * Created by xsf
 * on 2016.07.17:22
 */
public class LoadingDialog {
    /** 加载数据对话框 */
    public static XLoadingDialog mLoadingDialog;
    /**
     * 显示加载对话框
     * @param context 上下文
     * @param msg 对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {

        mLoadingDialog= XLoadingDialog.with(context)
                    .setBackgroundColor(Color.parseColor("#aa000000"))
                    .setMessageColor(Color.WHITE)
                    .setMessage(msg)
                    .setCanceled(cancelable);
        mLoadingDialog.show();

        return  mLoadingDialog;
    }

    public static Dialog showDialogForLoading(Activity context) {
        mLoadingDialog= XLoadingDialog.with(context)
                .setBackgroundColor(Color.parseColor("#aa000000"))
                .setMessageColor(Color.WHITE)
                .setCanceled(false);
        return  mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    public static void cancelDialogForLoading() {
        if(mLoadingDialog != null) {
            mLoadingDialog.cancel();
        }
    }
}
