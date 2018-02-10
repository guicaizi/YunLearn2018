package com.yun.software.corelib.Tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yun.software.corelib.R;
import com.yun.software.corelib.base.BaseApplication;

;


/**
 * Toast统一管理类
 */
public class ToastUitl {
    private static final int MSG_SHOW = 0x001;

    private static Handler mMainHandle = new Handler(Looper.getMainLooper(),
            new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg.what == MSG_SHOW && msg.obj != null) {
                        showToast((String) msg.obj, Toast.LENGTH_SHORT,0, Gravity.CENTER);
                    }
                    return true;
                }
            });

    /**
     * 提示信息
     *
     * @param msg
     */
    public static void showInfo(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            mMainHandle.sendMessage(mMainHandle.obtainMessage(MSG_SHOW, msg));
        } else {
            showToast(msg, Toast.LENGTH_SHORT,0, Gravity.CENTER);
        }

    }

    /**
     * 提示信息
     *
     */
    public static void showInfo(int resId) {
        if (TextUtils.isEmpty(getResString(resId))) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            mMainHandle.sendMessage(mMainHandle.obtainMessage(MSG_SHOW,getResString(resId)));
        } else {
            showToast(getResString(resId), Toast.LENGTH_SHORT,0, Gravity.CENTER);
        }
    }
    /**
     * 根据资源ID获取字符串资源
     *
     * @param resId
     * @return
     */
    public static String getResString( int resId) {
        return BaseApplication.getAppResources().getString(resId);
    }
    private static String lastToast = "";
    private static long lastToastTime;
    public static void showToast(String message, int duration, int icon,
                                 int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(BaseApplication.getAppContext()).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(BaseApplication.getAppContext());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }

}
