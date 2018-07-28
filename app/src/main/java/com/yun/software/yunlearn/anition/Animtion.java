package com.yun.software.yunlearn.anition;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2018/7/28 10:49
 */

public class Animtion extends BaseActivity {
    @Bind(R.id.iv_back1)
    ImageView ivBack1;
    @Bind(R.id.iv_back2)
    ImageView ivBack2;
    private Animation backAnimation1,backAnimation2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_animition;
    }

    @Override
    public void setData() {
        backAnimation1 = AnimationUtils.loadAnimation(this, R.anim.a);
        backAnimation2 = AnimationUtils.loadAnimation(this, R.anim.b);

    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;


    }

    @Override
    protected void onResume() {
        super.onResume();
        ivBack1.startAnimation(backAnimation1);
        ivBack2.startAnimation(backAnimation2);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ivBack1.clearAnimation();
        ivBack2.clearAnimation();
    }
}
