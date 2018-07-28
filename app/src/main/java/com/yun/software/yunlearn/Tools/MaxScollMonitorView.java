package com.yun.software.yunlearn.Tools;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by yanliang
 * on 2017/8/3 15:54
 */

public class MaxScollMonitorView extends ScrollView {
    private static final String TAG = "MyScrollview";

    private OnScrollListener listener;
    private int lastScrollY;
    public MaxScollMonitorView(Context context) {
        super(context);
    }

    public MaxScollMonitorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxScollMonitorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 监听scrollview的滑动位子
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(listener != null){
            listener.onScroll(lastScrollY = this.getScrollY());
        }
                switch(ev.getAction()){
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(1, 5);
                        break;
                }
        return super.onTouchEvent(ev);
    }


    public void setListener(OnScrollListener listener){
        this.listener = listener;
    }


//     * 手指离开后scrollview还会继续滑动，保证scrollY是最后停止时的y

     private Handler handler = new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             int scrollY = MaxScollMonitorView.this.getScrollY();
             if(lastScrollY != scrollY){
                 lastScrollY = scrollY;
                 handler.sendMessageDelayed(handler.obtainMessage(),5);
             }else{
                 return;
             }

             if(listener!=null){
                 listener.onScroll(scrollY);
             }
         }
    };


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener!=null){
            listener.onScroll(this.getScrollY());
        }
    }

    /**
     * scrollview 滑动的位置的回调接口
     */
    public interface OnScrollListener{

        public  void onScroll(int scrollY);
    }
}
