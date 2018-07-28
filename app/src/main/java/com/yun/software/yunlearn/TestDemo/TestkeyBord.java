package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2018/5/23 10:18
 */

public class TestkeyBord extends BaseActivity {
    @Bind(R.id.ettest)
    EditText ettest;
    @Bind(R.id.root)
    LinearLayout root;

    @Override
    public int getLayoutId() {
        return R.layout.keybordtest;
    }

    @Override
    public void setData() {
      controlKeyboardLayout(root,ettest);
    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void controlKeyboardLayout(final View root, final View needToScrollView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private Rect r = new Rect();

            @Override
            public void onGlobalLayout() {
                //获取当前界面可视部分
                TestkeyBord.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = TestkeyBord.this.getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                int heightDifference = screenHeight - r.bottom;

                Rect rect = new Rect();
                needToScrollView.getGlobalVisibleRect(rect);
                int viewKeepOut = heightDifference - (screenHeight - rect.bottom)+24;
                if (viewKeepOut > 0) {
                    needToScrollView.scrollTo(0, viewKeepOut);
                } else {
                    needToScrollView.scrollTo(0, 0);
                }
            }
        });
    }

}
