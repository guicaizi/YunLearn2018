package com.yun.software.yunlearn.shejimode;

/**
 * Created by yanliang
 * on 2018/4/4 10:10
 */

public class Test {
    @org.junit.Test
    public void testT(){


    }
    static MyCreatT people=new MyCreatT<People>(){

        @Override
        protected People create() {
            return null;
        }
    };
    @org.junit.Test
    public void TestOne(){
//        createBoundary();
        System.out.println(calc());

    }
    public static String createBoundary() {
        StringBuilder sb = new StringBuilder("----NoHttpFormBoundary");
        for (int t = 1; t < 12; t++) {
            long time = System.currentTimeMillis() + t;
            if (time % 3L == 0L) {
//                sb.append((char)(int) time % '\t' );
                System.out.println(time);
                System.out.println((char)time);
                System.out.println((int)'\t');
                System.out.println((int) time % '\t');
            } else if (time % 3L == 1L) {
                sb.append((char) (int) (65L + time % 26L));
            } else {
                sb.append((char) (int) (97L + time % 26L));
            }
        }
        return sb.toString();
    }

    public String calc() {
        StringBuilder sb = new StringBuilder("----NoHttpFormBoundary");
        for (int t = 1; t < 40; t++) {
            long time = System.currentTimeMillis() + t;
            System.out.println((char) (int) (65L + time % 26L));
//            System.out.println((char) (int) (97L + time % 26L));
            sb.append((char)(int) time % '\t');
        }
        return sb.toString();
    }


}
