package com.yun.software.yunlearn.ThreadPool;

import android.content.Context;
import android.os.Handler;

import com.yun.software.corelib.Tools.MyLogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanliang
 * on 2018/2/10 16:21
 * public ThreadPoolExecutor(int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue)
 * 重点解释 workQeue 对象
   BlockingQueue<Runnable> workQueue

 该线程池中的任务队列：维护着等待执行的Runnable对象

 当所有的核心线程都在干活时，新添加的任务会被添加到这个队列中等待处理，如果队列满了，则新建非核心线程执行任务

 常用的workQueue类型：

 SynchronousQueue：
 这个队列接收到任务的时候，会直接提交给线程处理，而不保留它，如果所有线程都在工作怎么办？
 那就新建一个线程来处理这个任务！所以为了保证不出现<线程数达到了maximumPoolSize而不能新建线程>的错误
 ，使用这个类型队列的时候，maximumPoolSize一般指定成Integer.MAX_VALUE，即无限大
 LinkedBlockingQueue：
 这个队列接收到任务的时候，如果当前线程数小于核心线程数，则新建线程(核心线程)处理任务；
 如果当前线程数等于核心线程数，则进入队列等待。由于这个队列没有最大值限制，即所有超过核心线程数的任务都将被添加到队列中，
 这也就导致了maximumPoolSize的设定失效，因为总线程数永远不会超过corePoolSize
 ArrayBlockingQueue：可以限定队列的长度，接收到任务的时候，如果没有达到corePoolSize的值，则新建线程(核心线程)执行任务，如果达到了，则入队等候，如果队列已满，则新建线程(非核心线程)执行任务，又如果总线程数到了maximumPoolSize，并且队列也满了，则发生错误
 DelayQueue：队列内元素必须实现Delayed接口，这就意味着你传进去的任务必须先实现Delayed接口。这个队列接收到任务时，首先先入队，只有达到了指定的延时时间，才会执行任务

 作者：LiuZh_
 链接：https://www.jianshu.com/p/210eab345423
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
        newFixedThreadPool = Executors.newFixedThreadPool(2);
        newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        newSingleThreadExecutor = Executors.newSingleThreadExecutor();


    }

    /**
     * ThreadFactory用法   操作要操作的线程
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

    public void excudeNewCachedTheadPool() {

         MyLogUtils.i("threaddemo","****************************创建一个可缓存线程池*******************************");
        for (int i = 0; i < 100; i++) {
            final int index = i;
            newCachedThreadPool.execute(new ThreadForpools(index));
        }

    }

    public void excudeNewFixedThreadPool() {
        //        可控制线程最大并发数（同时执行的线程数）
        //        超出的线程会在队列中等待
         MyLogUtils.i("threaddemo","****************************创建一个定长线程池*******************************");
        for (int i = 0; i < 4; i++) {
            final int index = i;
            newFixedThreadPool.execute(new ThreadForpools(index));
        }
    }

    public void excudeNewScheduledThreadPool() {
        MyLogUtils.i("threaddemo","**************************** 创建一个定长线程池*******************************");
         /**
         *
         *标准 5秒执行一次 和 任务执行时间无关
         *
         */

//        //
       final ScheduledFuture scheduledFuture= newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long time = (long) (Math.random() * 1000);
                    // 输出线程的名字和使用目标对象及休眠的时间
                    MyLogUtils.i("threaddemo",sf.format(new Date()) + " 线程：" + Thread.currentThread().getName() + ":Sleeping " + time + "ms");
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, 0, 5, TimeUnit.SECONDS);// 0 首次延迟时间  每隔5s
         /**
          *20秒后关闭， 应该执行5次
          */
         newScheduledThreadPool.schedule(new Runnable() {
            public void run() {
                scheduledFuture.cancel(true);
                newScheduledThreadPool.shutdown();
            }
         }, 20, TimeUnit.SECONDS);





        /**
         *cheduleWithFixedDelay() 跟任务执行时间有关 如果任务执行时间大于 延迟的 5秒 则 延迟无效 按照任务执行时间计算 ，否则 按照标准 延迟5秒执行
         *
         */
//        newScheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
//            //模拟耗时任务,耗时是10s以内的任意数
//            @Override
//            public void run() {
//                try {
//                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    long time = (long) (Math.random() * 1000);
//                    // 输出线程的名字和使用目标对象及休眠的时间
//                    MyLogUtils.i("threaddemo",sf.format(new Date()) + " 线程：" + Thread.currentThread().getName() + ":Sleeping " + time + "ms");
//                    Thread.sleep(time);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, 0, 5, TimeUnit.SECONDS);//每隔5s


    }

    public void excudeNewSingleThreadExecutor() {
        //        有且仅有一个工作线程执行任务
        //        所有任务按照指定顺序执行，即遵循队列的入队出队规则
         MyLogUtils.i("threaddemo","**************************创建一个单线程化的线程池*********************************");
        for (int i = 0; i < 4; i++) {
            final int index = i;
            newSingleThreadExecutor.execute(new ThreadForpools(index));
        }
    }
}
