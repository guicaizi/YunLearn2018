package com.yun.software.yunlearn.sufaAll;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by yanliang
 * on 2018/4/13 11:29
 */

public class TestSufa {
    @Test
    public void test01(){
         int arr[]={4,8,5,7,9} ;
         SufanTools.BubbleSort(arr);
         System.out.println(Arrays.toString(arr));


    }
    @Test
    public void test02(){
        int arr[]={4,8,5,7,9} ;
        SufanTools.xier(arr);
        System.out.println(Arrays.toString(arr));


    }
    //插入排序
    @Test
    public void test03(){
        int arr[]={4,8,5,7,9,6,15,11,12} ;
        SufanTools.insert_sort(arr);
        System.out.println(Arrays.toString(arr));






    }
    //希尔排序
    @Test
    public void test04(){
        int arr[]={4,8,5,7,9,6,15,11,12} ;
        SufanTools.shell_sort(arr);
        System.out.println(Arrays.toString(arr));
        int a= (int) 4.8;
        System.out.println(a);


    }
    @Test
    public void test05(){
        String str="http://localhost/aaa/bbb/ccc";
        if(str.contains("/")){
            int index=str.indexOf("/",7);
            System.out.println(index);

            System.out.println(str.substring(0,index));

        }
    }




}
