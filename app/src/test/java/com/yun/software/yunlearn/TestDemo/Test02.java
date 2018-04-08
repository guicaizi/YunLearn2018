package com.yun.software.yunlearn.TestDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by yanliang
 * on 2018/4/4 13:56
 */

public class Test02 {


    @Test
    public void test(){
        // 创建集合对象
        ArrayList array = new ArrayList();

        // 创建学生对象
        Student s1 = new Student("林青霞", 27);
        Student s2 = new Student("林志玲", 40);
        Student s3 = new Student("凤姐", 35);
        Student s4 = new Student("芙蓉姐姐", 18);
        Student s5 = new Student("翠花", 16);
        Student s6 = new Student("林青霞", 27);
        Student s7 = new Student("林青霞", 18);

        // 添加元素
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);
        array.add(s7);

        // 创建新集合
        ArrayList newArray = new ArrayList();

        // 遍历旧集合,获取得到每一个元素
        Iterator it = array.iterator();
        while (it.hasNext()) {
            Student s = (Student) it.next();

            // 拿这个元素到新集合去找，看有没有
            if (!newArray.contains(s)) {
                newArray.add(s);
            }
        }

        // 遍历新集合
        for (int x = 0; x < newArray.size(); x++) {
            Student s = (Student) newArray.get(x);
            System.out.println(s.getName() + "---" + s.getAge());
        }
    }

    @Test
    public void test02(){

        MyStack ms = new MyStack();

        // 添加元素
        ms.add("hello");
        ms.add("world");
        ms.add("java");

        // System.out.println(ms.get());
        // System.out.println(ms.get());
        // System.out.println(ms.get());
        // NoSuchElementException
        // System.out.println(ms.get());

        while(!ms.isEmpty()){
            System.out.println(ms.get());
        }


    }
    @Test
    public void test03(){
        Scanner sc = new Scanner(System.in);

        // 键盘录入多个数据,我们不知道多少个，所以用集合存储
        ArrayList<Integer> array = new ArrayList<Integer>();

        // 以0结束,这个简单，只要键盘录入的数据是0，我就不继续录入数据了
        while (true) {
            System.out.println("请输入数据：");
            int number = sc.nextInt();
            if (number != 0) {
                array.add(number);
            } else {
                break;
            }
        }

        // 把集合转成数组
        // public <T> T[] toArray(T[] a)



        Integer[] i = new Integer[array.size()];
        // Integer[] ii = array.toArray(i);
        array.toArray(i);
        // System.out.println(i);
        // System.out.println(ii);

        // 对数组排序
        // public static void sort(Object[] a)
        Arrays.sort(i);

        // 获取该数组中的最大索引的值
        System.out.println("数组是：" + arrayToString(i) + "最大值是:"
                + i[i.length - 1]);
    }

    public static String arrayToString(Integer[] i) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (int x = 0; x < i.length; x++) {
            if (x == i.length - 1) {
                sb.append(i[x]);
            } else {
                sb.append(i[x]).append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    @Test
    public void  test04(){
        Set<String> set = new HashSet<>();

        // 创建并添加元素
        set.add("hello");
        set.add("java");
        set.add("world");
        set.add("java");
        set.add("world");

        // 增强for
        for (String s : set) {
            System.out.println(s);
        }
    }
    @Test
    public void test05(){



        HashMap<Student1, String> hm = new HashMap<Student1, String>();

        // 创建学生对象
        Student1 s1 = new Student1("貂蝉", 27);
        Student1 s2 = new Student1("王昭君", 30);
        Student1 s3 = new Student1("西施", 33);
        Student1 s4 = new Student1("杨玉环", 35);
        Student1 s5 = new Student1("貂蝉", 27);

        // 添加元素
        hm.put(s1, "8888");
        hm.put(s2, "6666");
        hm.put(s3, "5555");
        hm.put(s4, "7777");
        hm.put(s5, "9999");

        // 遍历
        Set<Student1> set = hm.keySet();
        for (Student1 key : set) {
            String value = hm.get(key);
            System.out.println(key.getName() + "---" + key.getAge() + "---"
                    + value);
        }
    }

    @Test
    public void test06(){
        LinkedHashMap<String, String> hm = new LinkedHashMap<>();

        // 创建元素并添加元素
        // String key1 = "it001";
        // String value1 = "马云";
        // hm.put(key1, value1);

        hm.put("it001", "马云");
        hm.put("it003", "马化腾");
        hm.put("it004", "乔布斯");
        hm.put("it005", "张朝阳");
        hm.put("it002", "裘伯君"); // wps


        // 遍历
        Set<String> set = hm.keySet();
        for (String key : set) {
            String value = hm.get(key);
            System.out.println(key + "---" + value);
        }
    }










    }






