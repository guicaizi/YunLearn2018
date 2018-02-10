package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.yun.software.corelib.Tools.NumberUtil;
import com.yun.software.corelib.Tools.TopBarUtil;
import com.yun.software.corelib.Tools.WeakRefHander;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2017/12/4 16:08
 */

public class BigNumberDemo extends BaseActivity {
   @Bind(R.id.showresult)
    EditText showResult;
   @Bind(R.id.btn_send)
    Button btnSend;
   @Bind(R.id.ratingbar)
    RatingBar ratingbar;
    WeakRefHander handler=new WeakRefHander(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
          showResult.setText(msg.arg1+"");
            return false;
        }
    });

    @Override
    public int getLayoutId() {
        return R.layout.activity_bignumber;
    }

    @Override
    public void setData() {

        TopBarUtil.initBtnBack(this, R.id.tv_base_back);
        TopBarUtil.initTitle(this, R.id.tv_base_title, "大数运算");
        double a=1.256988554455555555555879;
        showResult.setText(NumberUtil.getAmountValue(a));
    }

    @Override
    public void addLisener() {
     btnSend.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           new Thread(new Runnable() {
               @Override
               public void run() {
                   Message message=new Message();
                   message.what=2;
                   message.arg1=2;

                   handler.start(2,1000);



               }
           }).start();
         }
     });
     ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
         @Override
         public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
             System.out.println("等级：" + rating);
             System.out.println("星星：" + ratingBar.getNumStars());
         }
     });


    }


    @Override
    public Activity getActivity() {
        return null;
    }
}
