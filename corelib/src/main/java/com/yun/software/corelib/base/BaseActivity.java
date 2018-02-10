package com.yun.software.corelib.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.yun.software.corelib.AllControler.LoadingDialog;
import com.yun.software.corelib.AllControler.ThreadManager;
import com.yun.software.corelib.R;
import com.yun.software.corelib.Tools.StringUtils;
import com.yun.software.corelib.Tools.ToastUitl;
import com.yun.software.corelib.theambar.StatusBarUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2017/11/29 14:56
 */

public abstract class BaseActivity extends FragmentActivity implements commenBase {
    public  static final String TAG = BaseActivity.class.getSimpleName();
    public BaseActivity mContext;
    private boolean isAlive = false;

    private boolean isRunning = false;

    private boolean isConfigChange=false;
    /**
     * layout params
     */
    public static final int MATCH_PARENT = -1;

    /**
     * layout params
     */
    public static final int WRAP_CONTENT = -2;
    /**
     * Fragment管理器
     *
     * @warn 不能在子类中创建
     */
    protected FragmentManager fragmentManager = null;
    private Handler mUiHandler;

    /**
     * 线程名列表
     */
    protected List<String> threadNameList;
    private static class UiHandler extends Handler {
        private final WeakReference<BaseActivity> mActivityReference;

        public UiHandler(BaseActivity activity) {
            mActivityReference = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActivityReference.get() != null) {
                mActivityReference.get().handleUiMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApplication.getInstance().addActivity(this);
        mUiHandler = new UiHandler(this);
        mContext =(BaseActivity) getActivity();
        threadNameList = new ArrayList<String>();
        fragmentManager = getSupportFragmentManager();
        setContentView(getLayoutId());
        setDefaulTheambar();
        ButterKnife.bind(this);
        setData();
        addLisener();
    }
    public void setDefaulTheambar(){
        StatusBarUtil.setColor(this,getResourceColor(R.color.white),115);
    }
    public abstract Activity getActivity();
    protected void sendUiMessage(Message msg) {
        mUiHandler.sendMessage(msg);
    }

    protected void sendUiMessageDelayed(Message msg, long delayMillis) {
        mUiHandler.sendMessageDelayed(msg, delayMillis);
    }

    protected void sendEmptyUiMessage(int what) {
        mUiHandler.sendEmptyMessage(what);
    }

    protected void sendEmptyUiMessageDelayed(int what, long delayMillis) {
        mUiHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

    protected void removeUiMessages(int what) {
        mUiHandler.removeMessages(what);
    }

    @Override
    public void handleUiMessage(Message msg) {

    }

    @Override
    public void enterPage(Class<?> clazz) {
        if (clazz == null) {
            return;
        }
        enterPage(clazz,null);
    }

    @Override
    public void enterPage(Class<?> clazz, Bundle bundle) {
        if (clazz == null) {
            return;
        }
        enterPageForResult(clazz,bundle,0);
    }


    @Override
    public void enterPageForResult(Class<?> clazz, int requestCode) {
        if (clazz == null) {
            return;
        }
        enterPageForResult(clazz,null,requestCode);
    }

    @Override
    public void enterPageForResult(Class<?> clazz, Bundle bundle, int requestCode) {
        if (clazz == null) {
            return;
        }
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtra(AppConstans.START_ACTIVITY_PUTEXTRA, bundle);
        }
        if (requestCode > 0) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void showProgressDialog(final String msg) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (LoadingDialog.mLoadingDialog != null && LoadingDialog.mLoadingDialog.isShowing() == true) {
                    LoadingDialog.cancelDialogForLoading();
                }
                if (StringUtils.isNotEmpty(msg)) {
                    LoadingDialog.showDialogForLoading(mContext,msg, true);
                }
            }
        });
    }

    /**
     * 停止浮动加载进度条
     */
    public void dismissProgressDialog() {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (LoadingDialog.mLoadingDialog != null && LoadingDialog.mLoadingDialog.isShowing() == true) {
                    LoadingDialog.cancelDialogForLoading();
                }
            }
        });
    }
    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param stringResId
     */
    public void showShortToast(int stringResId) {
        try {
            ToastUitl.showInfo(stringResId);
        } catch (Exception e) {
            Log.e(TAG, "showShortToast  context.getResources().getString(resId)" +
                    " >>  catch (Exception e) {" + e.getMessage());
        }
    }

    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param string
     */
    public void showShortToast(String string) {
        ToastUitl.showInfo(string);
    }

    /**
     * 快速关闭progressdialog 让其显示shootoast
     * @param string
     * @param isForceDismissProgressDialog
     */
    public void showShortToast(final String string, final boolean isForceDismissProgressDialog) {
        runUiThread(new Runnable() {
            @Override
            public void run() {
                if (isForceDismissProgressDialog) {
                    dismissProgressDialog();
                }
                ToastUitl.showInfo(string);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange=true;

    }
    /**
     * 在UI线程中运行，建议用这个方法代替runOnUiThread
     *
     * @param action
     */
    public final void runUiThread(Runnable action) {
        if (isAlive() == false) {
            Log.w(TAG, "runUiThread  isAlive() == false >> return;");
            return;
        }
        runOnUiThread(action);
    }
    public final boolean isAlive() {
        return isAlive && mContext!= null;// & ! isFinishing();导致finish，onDestroy内runUiThread不可用
    }
    /**
     * 运行线程
     *
     */
    public final Handler runThread(String name, Runnable runnable) {
        if (isAlive() == false) {
            Log.w(TAG, "主线程不存在");
            return null;
        }
        name = StringUtils.getTrimedString(name);
        Handler handler = ThreadManager.getInstance().runThread(name, runnable);
        if (handler == null) {
            Log.e(TAG, "运行handler 为空");
            return null;
        }

        if (threadNameList.contains(name) == false) {
            threadNameList.add(name);
        }
        return handler;
    }

    /**
     * 获取资源文件的颜色值
     *
     * @param id
     * @return
     */
    protected int getResourceColor(int id) {
        return getResources().getColor(id);
    }

    /**
     * 获取资源文件的尺寸
     *
     * @param id
     * @return
     */
    protected float getDimension(int id) {
        return getResources().getDimension(id);
    }

    /**
     * 获取资源文件int数组
     *
     * @param id
     * @return
     */
    protected int[] getIntArray(int id) {
        return getResources().getIntArray(id);
    }

    /**
     * 获取资源文件string数组
     *
     * @param id
     * @return
     */
    protected String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }
    @Override
    protected void onDestroy() {
        isAlive=false;
        isRunning=false;
        ThreadManager.getInstance().destroyThread(threadNameList);
        BaseApplication.getInstance().removeActivity(this.getClass().getSimpleName());
        threadNameList = null;
        fragmentManager=null;
        mContext = null;
        ButterKnife.unbind(this);
        super.onDestroy();

    }
}
