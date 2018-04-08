package com.yun.software.yunlearn.CloseLock;

/**
 * Created by yanliang
 * on 2018/3/27 09:44
 */

public class LockThread implements Runnable {

    int flag = 0;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
//        if (flag == 1) {
//            synchronized (LockInstance.objA) {
//                System.out.println("----拿到对象A");
//                synchronized (LockInstance.objB) {
//                    System.out.println("-----需要对象B");
//                }
//            }
//        } else {

            try {
                System.out.println("2222222222222拿到对象c");
//                synchronized (LockInstance.objB) {

                    Thread.sleep(1000);
//                    synchronized (LockInstance.objA) {
                        System.out.println("22222222222需要对象A");
//                    }

//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//    }
}
