package com.yun.software.yunlearn.TestDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * Created by yanliang
 * on 2018/4/3 14:48
 */

public class Test01 {

    @Test
    public void test(){
        final int count = 10; // 计数次数
        final CountDownLatch latch = new CountDownLatch(count);
//        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // do anything
                        for (int i = 0; i <10 ; i++) {
                            System.out.println("线程"
                                    + Thread.currentThread().getId());
                            Thread.sleep(1000);
                            latch.countDown();
                        }

                    } catch (Throwable e) {
                        // whatever
                    } finally {
                        // 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放

                    }
                }
            }).start();
//        }
        try {
            // 10个线程countDown()都执行之后才会释放当前线程,程序才能继续往后执行
            latch.await();
        } catch (InterruptedException e) {
            // whatever
        }
        System.out.println("Finish");
    }

    @Test
    public void test03(){
        Collection c=new ArrayList<String>();
        Iterator iterator=c.iterator();

    }


}
