package com.yun.software.yunlearn.Filehandler;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by yanliang
 * on 2018/4/13 14:57
 */

public class FileTest {


    @Test
    public void testfile(){

        File file=new File("D:\\yan.txt");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

         OutputStream out = null;
         InputStream  is=null;
        try {
            out = new FileOutputStream(file,true);
            is=new FileInputStream(file);

            for (int x = 0; x < 10; x++) {
                out.write(("冷冬梅" + x).getBytes());
                out.write("\r\n".getBytes());

            }
            // 最终版代码
            int by = 0;
            // 读取，赋值，判断
            while ((by = is.read()) != -1) {
                System.out.print((char) by);
            }

            // 释放资源


        } catch (Exception e) {
            //把异常输出到指定文件
            e.printStackTrace(new PrintStream(out));
        }finally {
            try {
                out.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Test
    public void test02(){


        try {
            hasException();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void  hasException() throws Exception{

        int a = 10;
        // int b = 2;
        int b = 0;
         if(b==0){
             throw new MyExcepiton("b不能为0");
         }
        //        try {
        System.out.println(a / b);
        //        } catch (Exception e) {
        ////            System.out.println(e.toString());
        //            System.out.println(e.getMessage());
        //            e.printStackTrace();
        ////            e.toString();
        //
        //        }

        // 第二阶段
        System.out.println("over");


    }

    @Test
    public void test03(){
        try {
//            String s = "我爱你中国";
//            // [-50, -46, -80, -82, -60, -29, -42, -48, -71, -6]
//
//            byte[] bys = s.getBytes();
//            System.out.println(Arrays.toString(bys));

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                    "D:\\yan.txt"));

            // 读取数据
            // int by = 0;
            // while ((by = bis.read()) != -1) {
            // System.out.print((char) by);
            // }
            // System.out.println("---------");

            byte[] bys = new byte[1024];
            int len = 0;
            while ((len = bis.read(bys)) != -1) {
                System.out.print(new String(bys, 0, len));
            }

            // 释放资源
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public  void write()  {
        // 创建字符缓冲输出流对象
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\yan.txt"));
            for (int x = 0; x < 10; x++) {
                bw.write("hello" + x);
                // bw.write("\r\n");
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



