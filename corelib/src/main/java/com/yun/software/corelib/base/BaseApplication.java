
package com.yun.software.corelib.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jakechen on 2015/8/5.
 */
public abstract class BaseApplication extends Application {
    public static BaseApplication mInstance;

    private static RefWatcher watcher;
    private static boolean isWatcherRef = false;
    private HashMap<String, WeakReference<Activity>> mActivityMap;

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        onRelease();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = getChildInstance();
        mActivityMap = new HashMap<String, WeakReference<Activity>>();
        if (isWatcherRef) {
            watcher = LeakCanary.install(this);
        }
        onConfig();
    }
    public static Resources getAppResources() {
        return mInstance.getResources();
    }
    public static Context getAppContext() {
        return mInstance;
    }
    public static void setWatcher(Object obj) {
        if (isWatcherRef && watcher != null) {
            watcher.watch(obj);
        }
    }

    public static void setIsWatcherRef(boolean enable) {
        isWatcherRef = enable;
    }

    protected abstract void onConfig();

    protected abstract void onRelease();


    public void addActivity(Activity activity) {
        if (activity != null) {
            mActivityMap.put(activity.getClass().getSimpleName(), new WeakReference<Activity>(
                    activity));
            setWatcher(activity);
            if (activity instanceof FragmentActivity) {
                FragmentActivity fragmentActivity = (FragmentActivity) activity;
                List<Fragment> fragments = fragmentActivity.getSupportFragmentManager().getFragments();
                if (fragments != null && fragments.size() > 0) {
                    for (int i = 0; i < fragments.size(); i++) {
                        setWatcher(fragments.get(i));
                    }
                }
            }
        }
    }

    public void removeActivity(String activityName) {
        if (mActivityMap != null && mActivityMap.containsKey(activityName)) {
            mActivityMap.remove(activityName);
        }
    }

    public void exitApp() {
        if (mActivityMap != null && mActivityMap.size() > 0) {
            for (String key : mActivityMap.keySet()) {
                if (mActivityMap.get(key) != null) {
                    mActivityMap.get(key).get().finish();
                }
            }
        }
        mActivityMap.clear();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public Context getContext() {
        return getApplicationContext();
    }

    protected abstract BaseApplication getChildInstance();

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }
}
