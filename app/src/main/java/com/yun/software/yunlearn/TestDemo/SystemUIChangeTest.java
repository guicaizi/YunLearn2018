package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/1/3 17:03
 */

public class SystemUIChangeTest extends BaseActivity {
    @Bind(R.id.btn_full)
    Button btnFull;
    @Bind(R.id.btn_quit)
    Button btnQuit;
    @Bind(R.id.change_oritention)
    Button changeOritention;

    @Override
    public int getLayoutId() {
        return R.layout.systemuichangetest;
    }

    @Override
    public void setData() {

    }

    @Override
    public void addLisener() {
        btnFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFullScreen(mContext);
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitFullScreen(mContext);
            }
        });
        changeOritention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullScreen(mContext);

            }
        });

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 设置全屏
     */
    public void setFullScreen(Activity activity) {
        // 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
        if (activity != null) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 退出全屏
     */
    public void quitFullScreen(Activity activity) {
        // 声明当前屏幕状态的参数并获取
        if (activity != null) {
            final WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attrs);
            activity.getWindow()
                    .clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 全屏回调
     *
     * @param activity
     */
    public void fullScreen(Activity activity) {
        if (activity != null) {
            if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
    }
    /**
     * 得到Activity根View
     *
     * @param activity
     * @return
     */
    private FrameLayout getDecorView(Activity activity) {

        if (activity != null && activity.getWindow() != null) {
            Window window = activity.getWindow();
            ViewGroup decorView = (ViewGroup) window.getDecorView().findViewById(android.R.id.content);
            if (decorView == null) {
                decorView = (ViewGroup) window.getDecorView();
            }
            if (decorView != null && decorView instanceof FrameLayout) {
                return (FrameLayout) decorView;
            }
        }
        return null;
    }

}
