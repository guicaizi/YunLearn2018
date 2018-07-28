package com.yun.software.javatest;

public class myClass {
    public static void main(String[] arg){
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //使用stop()方法，强制停止线程
            t.stop();
        }

    }
    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {

            while (true) {
                synchronized (myClass.class) {
                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println(user.toString());
                    }
                }
                // 让出CPU，给其他线程执行
                Thread.yield();
            }

        }
    }
    public static User user = new User();

    // 改变user变量的线程
    public static class ChangeObjectThread extends Thread {
        @Override
        public void run() {

            while (true) {
                synchronized (myClass.class) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    user.setId(v);
                    // to do sth
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
                // 让出CPU，给其他线程执行
                Thread.yield();
            }

        }

    }

}
