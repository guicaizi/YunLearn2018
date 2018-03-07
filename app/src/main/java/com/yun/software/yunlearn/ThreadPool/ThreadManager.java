package com.yun.software.yunlearn.ThreadPool;

import android.content.Context;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanliang
 * on 2018/2/10 16:21
 */

public class ThreadManager {
    private Handler uiHandler;
    private static ThreadManager instance;
    ExecutorService newCachedThreadPool;
    ExecutorService newFixedThreadPool;
    ExecutorService newSingleThreadExecutor;
    ScheduledExecutorService newScheduledThreadPool;
    public ThreadManager(Context context) {
        uiHandler = new Handler(context.getMainLooper());
        newCachedThreadPool = Executors.newCachedThreadPool();
        newFixedThreadPool =Executors.newFixedThreadPool(2);
        newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        newSingleThreadExecutor = Executors.newSingleThreadExecutor();








    }
       /**
        *ThreadFactory用法   操作要操作的线程
        */
//    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(4, new ThreadFactory() {
//        @Override
//        public Thread newThread(@NonNull Runnable r) {
//
//            return new Thread(r){
//                @Override
//                public void run() {
//                    Log.e("lzp", "newThread");
//                    super.run();
//                    Log.e("lzp", "newThread over");
//                }
//            };
//        }
//    });
//
//    private ThreadFactory threadFactory = new ThreadFactory() {
//        @Override
//        public Thread newThread(@NonNull final Runnable r) {
//            return new Thread() {
//                @Override
//                public void run() {
//                    r.run();
//                    Log.e("lzp", "嘿嘿嘿");
//                }
//            };
//        }
//    };



    public synchronized static ThreadManager getInstance(Context context) {
        if (instance == null) {
            instance = new ThreadManager(context);
        }

        return instance;
    }
//    线程数无限制
//    有空闲线程则复用空闲线程，若无空闲线程则新建线程
//    一定程序减少频繁创建/销毁线程，减少系统开销

    public void excudeNewCachedTheadPool(){

        System.out.println("****************************创建一个可缓存线程池*******************************");
        for(int i=0;i<100;i++)
        {
            final int index=i;
            newCachedThreadPool.execute(new ThreadForpools(index));
        }

    }
    public void excudeNewFixedThreadPool(){
//        可控制线程最大并发数（同时执行的线程数）
//        超出的线程会在队列中等待
        System.out.println("****************************创建一个定长线程池*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            newFixedThreadPool.execute(new ThreadForpools(index));
        }
    }
    public void excudeNewScheduledThreadPool(){
//        支持定时及周期性任务执行。

//        第四种线程池是ScheduledExecutorService，我平时没有用过，他的最大优点除了线程池的特性以外，可以实现循环或延迟任务。
        System.out.println("**************************** 创建一个定长线程池*******************************");
//        for(int i=0;i<4;i++)
//        {
//            final int index=i;
////            newScheduledThreadPool.schedule(new ThreadForpools(index),3, TimeUnit.SECONDS);
//
//
//        }
        /**
         *initialDelay  一个周期延迟多长时间
         * period 周期间隔
         * 按照上一次任务的发起时间计算下一次任务的开始时间
         * 开始时间 + 延迟时间 = 下一个任务的开始时间。
         * scheduleAtFixedRate(command,5,10,TimeUnit.SECONDS)这个方法的周期性会受 command的影响，
         * 如果command方法的执行时间是10秒，那么执行command的周期其实是20秒，
         * 即 scheduleAtFixedRate这个方法要等一个完整的command方法执行完成后才继续周期性地执行command方法，其实这样的设计也是 符合常理的。
         *  当间隔时间（5s）大于任务的执行时间（3s），运行结果为：
         12:00:03 开始执行, 12:00:06结束执行 ================

         12:00:08 开始执行, 12:00:11结束执行 ================

         12:00:13 开始执行, 12:00:16结束执行 ================

         12:00:18 开始执行, 12:00:21结束执行 ================

         12:00:23 开始执行, 12:00:26结束执行 ================

         12:00:28 开始执行, 12:00:31结束执行 ================



         当间隔时间（5s）小于程序（7s）执行时间（将耗时改为7s, 每隔5s的设置就会失效）

         12:01:26 开始执行, 12:01:33结束执行 ================

         12:01:33 开始执行, 12:01:40结束执行 ================

         12:01:40 开始执行, 12:01:47结束执行 ================

         12:01:47 开始执行, 12:01:54结束执行 ================

         12:01:54 开始执行, 12:02:01结束执行 ================

         12:02:01 开始执行, 12:02:08结束执行 ================

         说明：scheduleAtFixedRate是以上一次任务的开始时间为间隔的，并且当任务执行时间大于设置的间隔时间时，真正间隔的时间由任务执行时间为准！
         *
         *
         *
         */

//
        newScheduledThreadPool.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    System.out.print(sdf.format(new Date()) + " 开始执行, ");
                    Thread.sleep(3000);//3s
                    System.out.println(sdf.format(new Date()) + "结束执行 ================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, 0, 5, TimeUnit.SECONDS);//每隔5s
        /**
         *
         */
        newScheduledThreadPool.scheduleWithFixedDelay(new Runnable(){
        //模拟耗时任务,耗时是10s以内的任意数
        @Override
        public void run() {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                System.out.print(sdf.format(new Date()) + " 开始执行, ");
                Thread.sleep(3000);//3s
                System.out.println(sdf.format(new Date()) + "结束执行 ================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }, 0, 5, TimeUnit.SECONDS);//每隔5s










    }
    public void excudeNewSingleThreadExecutor(){
//        有且仅有一个工作线程执行任务
//        所有任务按照指定顺序执行，即遵循队列的入队出队规则
        System.out.println("**************************创建一个单线程化的线程池*********************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            newSingleThreadExecutor.execute(new ThreadForpools(index));
        }
    }
}
