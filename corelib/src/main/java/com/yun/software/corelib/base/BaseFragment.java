package com.yun.software.corelib.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by yanliang
 * on 2017/11/29 17:17
 */

public abstract  class BaseFragment extends Fragment implements commenBase {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    static final String ARGUMENT_POSITION = "ARGUMENT_POSITION";
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    private Handler mUiHandler;
    private static final String TAG = BaseFragment.class.getSimpleName();
    protected View rootView;
    private boolean isRunning = false;
    private boolean isAlive = false;
    protected BaseActivity mContext = null;
    private static class UiHandler extends Handler {
        private final WeakReference<BaseFragment> mActivityReference;

        public UiHandler(BaseFragment activity) {
            mActivityReference = new WeakReference<BaseFragment>(activity);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUiHandler = new UiHandler(this);
        mContext =(BaseActivity) getActivity();
        isAlive = true;
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        ButterKnife.bind(this, rootView);
        setData();
        addLisener();
        return rootView;
    }
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

    protected Message obtainUiMessage() {
        return mUiHandler.obtainMessage();
    }

    @Override
    public void handleUiMessage(Message msg) {

    }

    @Override
    public void enterPage(Class<?> clazz) {
        enterPage(clazz, null);
    }

    @Override
    public void enterPage(Class<?> clazz, Bundle bundle) {
        if (clazz == null) {
            return;
        }
        enterPageForResult(clazz, bundle, 0);
    }

    @Override
    public void enterPageForResult(Class<?> clazz, int requestCode) {
        if (clazz == null) {
            return;
        }
        enterPageForResult(clazz, null, 0);
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
        mContext.runUiThread(action);
    }
    protected Bundle argument = null;
    /**
     * 运行线程
     *
     * @param name
     * @param runnable
     * @return
     */
    public final Handler runThread(String name, Runnable runnable) {
        if (isAlive() == false) {
            Log.w(TAG, "runThread  isAlive() == false >> return null;");
            return null;
        }
        return mContext.runThread(name + getPosition(), runnable);
    }

    /**
     * 该Fragment在Activity添加的所有Fragment中的位置，通过ARGUMENT_POSITION设置
     *
     * @must 只使用getPosition方法来获取position，保证position正确
     */
    private int position = -1;

    /**
     * 获取该Fragment在Activity添加的所有Fragment中的位置
     */
    public int getPosition() {
        if (position < 0) {
            argument = getArguments();
            if (argument != null) {
                position = argument.getInt(ARGUMENT_POSITION, position);
            }
        }
        return position;
    }

    public final boolean isAlive() {

        return isAlive && mContext != null;// & ! isRemoving();导致finish，onDestroy内runUiThread不可用
    }
    /**
     * 展示加载进度条,无标题
     *
     * @param stringResId
     */
    public void showProgressDialog(int stringResId) {
        if (isAlive() == false) {
            Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showProgressDialog(mContext.getResources().getString(stringResId));
    }

    /**
     * 展示加载进度条,无标题
     *
     * @param dialogMessage
     */
    public void showProgressDialog(String dialogMessage) {
        if (isAlive() == false) {
            Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showProgressDialog(dialogMessage);
    }

    /**
     * 隐藏加载进度
     */
    public void dismissProgressDialog() {
        if (isAlive() == false) {
            Log.w(TAG, "dismissProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.dismissProgressDialog();
    }
    //show short toast<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param stringResId
     */
    public void showShortToast(int stringResId) {
        if (isAlive() == false) {
            Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showShortToast(stringResId);
    }

    /**
     * 快捷显示short toast方法，需要long toast就用 Toast.makeText(string, Toast.LENGTH_LONG).show(); ---不常用所以这个类里不写
     *
     * @param string
     */
    public void showShortToast(String string) {
        if (isAlive() == false) {
            Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showShortToast(string);
    }

    /**
     * 强迫关闭progressdialog\
     *
     * @param string
     * @param isForceDismissProgressDialog
     */
    public void showShortToast(String string, boolean isForceDismissProgressDialog) {
        if (isAlive() == false) {
            Log.w(TAG, "showProgressDialog  isAlive() == false >> return;");
            return;
        }
        mContext.showShortToast(string, isForceDismissProgressDialog);
    }
    /**
     * 获取资源文件的颜色值
     *
     * @param id
     * @return
     */
    protected int getColor(int id) {
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
    public void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRunning = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        isAlive = false;
        mContext = null;
        rootView = null;
        super.onDestroy();
    }
}
