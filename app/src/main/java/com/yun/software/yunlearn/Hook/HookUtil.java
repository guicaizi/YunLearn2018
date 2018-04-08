package com.yun.software.yunlearn.Hook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by baby on 2018/4/2.
 */

public class HookUtil {

    private Context context;

    public void hookHookMh(Context context) {
        try {
            Class<?> forName = Class.forName("android.app.ActivityThread");
            Field currentActivityThreadField = forName.getDeclaredField("sCurrentActivityThread");
            currentActivityThreadField.setAccessible(true);
            //            还原系统的ActivityTread   mH
            Object activityThreadObj = currentActivityThreadField.get(null);

            Field handlerField = forName.getDeclaredField("mH");
            handlerField.setAccessible(true);
            //            hook点找到了
            Handler mH = (Handler) handlerField.get(activityThreadObj);
            Field callbackField = Handler.class.getDeclaredField("mCallback");

            callbackField.setAccessible(true);

            callbackField.set(mH, new ActivityMH(mH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hookStartActivity(Context context) {
        //        还原 gDefault 成员变量  反射  调用一次
        this.context = context;
        try {
            Class<?> ActivityManagerNativecls = Class.forName("android.app.ActivityManagerNative");
            Field gDefault = ActivityManagerNativecls.getDeclaredField("gDefault");
            gDefault.setAccessible(true);
            //            因为是静态变量  所以获取的到的是系统值  hook   伪hook
            // 获取 Singhleton 对象  private static final Singleton<IActivityManager> gDefault = new Singleton<IActivityManager>()
            Object defaltValue = gDefault.get(null);
            //mInstance对象
            Class<?> SingletonClass = Class.forName("android.util.Singleton");

            Field mInstance = SingletonClass.getDeclaredField("mInstance");
            //        还原 IactivityManager对象  系统对象
            mInstance.setAccessible(true);
//            public abstract class Singleton<T> {
//                private T mInstance;
//
//                protected abstract T create();
//
//                public final T get() {
//                    synchronized (this) {
//                        if (mInstance == null) {
//                            mInstance = create();
//                        }
//                        return mInstance;
//                    }
//                }
//            }

            Object iActivityManagerObject = mInstance.get(defaltValue);

           // 获取IActivityManager 接口 方便动态代理
            Class<?> IActivityManagerIntercept = Class.forName("android.app.IActivityManager");
            startActivty startActivtyMethod = new startActivty(iActivityManagerObject);
            //            第二参数  是即将返回的对象 需要实现那些接口
            Object oldIactivityManager = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                    , new Class[]{IActivityManagerIntercept, View.OnClickListener.class}
                    , startActivtyMethod);
            //   将系统的iActivityManager  替换成    自己通过动态代理实现的对象   oldIactivityManager对象  实现了 IActivityManager这个接口的所有方法
            mInstance.set(defaltValue, oldIactivityManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ActivityMH implements Handler.Callback {
        private Handler mH;

        public ActivityMH(Handler mH) {
            this.mH = mH;
        }

        @Override
        public boolean handleMessage(Message msg) {
            //LAUNCH_ACTIVITY ==100 即将要加载一个activity了
            if (msg.what == 100) {
                //加工 --完  一定丢给系统  secondActivity  -hook->proxyActivity---hook->    secondeActivtiy
                handleLuachActivity(msg);
            }
            //做了真正的跳转
            mH.handleMessage(msg);
            return true;
        }

        private void handleLuachActivity(Message msg) {
            //            还原
            Object obj = msg.obj;
            try {
                Field intentField = obj.getClass().getDeclaredField("intent");
                intentField.setAccessible(true);
                //  ProxyActivity   2
                Intent realyIntent = (Intent) intentField.get(obj);
                //                sconedActivity  1
                Intent oldIntent = realyIntent.getParcelableExtra("oldIntent");
                if (oldIntent != null) {
                    //                    集中式登录
                    SharedPreferences share = context.getSharedPreferences("david",
                            Context.MODE_PRIVATE);
//                    if (share.getBoolean("login", false) || oldIntent.getComponent().getClassName().equals(SceondActivity.class.getName())) {
//
//                        //                      登录  还原  把原有的意图    放到realyIntent
//                        realyIntent.setComponent(oldIntent.getComponent());
//                    } else {
//                        ComponentName componentName = new ComponentName(context, LoginActivity.class);
//                        realyIntent.putExtra("extraIntent", oldIntent.getComponent()
//                                .getClassName());
//                        realyIntent.setComponent(componentName);
//                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    class startActivty implements InvocationHandler {
        private Object iActivityManagerObject;

        public startActivty(Object iActivityManagerObject) {
            this.iActivityManagerObject = iActivityManagerObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            Log.i("INFO", "invoke    " + method.getName());
            if ("startActivity".equals(method.getName())) {
                Log.i("INFO", "-----------------startActivity--------------------------");
                //瞒天过海
                //                寻找传进来的intent
                Intent intent = null;
                int index = 0;
                for (int i = 0; i < args.length; i++) {
                    //                    intent
                    Object arg = args[i];
                    if (arg instanceof Intent) {
                        intent = (Intent) args[i];
                        index = i;
                    }
                }
//                //目的  ---载入acgtivity  将它还原
                Intent newIntent = new Intent();
                ComponentName componentName = new ComponentName(context, HookActivity.class);
                newIntent.setComponent(componentName);
                //                真实的意图 被我隐藏到了  键值对
                newIntent.putExtra("oldIntent", intent);
                args[index] = newIntent;
            }

            return method.invoke(iActivityManagerObject, args);
        }
    }

}
