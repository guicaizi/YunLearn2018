package com.yun.software.yunlearn.TestDemo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by yanliang
 * on 2018/5/16 09:54
 */

public class Test03 {

    @Test
    public void test(){
        LinkedHashMap linkedHashMap=new LinkedHashMap();
        linkedHashMap.put(1,"12");
        linkedHashMap.put(2,"25");
        linkedHashMap.put(1,"22");

        System.out.println(linkedHashMap.toString());



    }
    @Test
    public void test02(){


        Date date = null;
        try {
            String str="017-09-14T02:00:00".replace("T"," ");
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str);
            String now = new SimpleDateFormat("yyyy年MM月dd日 hh:mm").format(date);
            System.out.println(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }






    }
    public String getStringByFormat(String strDate, String format,String format2) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format2);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }
    @Test
    public void test06(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i%2);

        }


    }
    @Test
  public void test07(){
        Student student=new Student("张三",22);
        Student student2=new Student("张三",27);
        Student student3=new Student("张三",22);
        Student student4=new Student("张三",8);
        Student student5=new Student("张三",13);
        List<Student> lista=new ArrayList();
        lista.add(student);
        lista.add(student2);
        lista.add(student3);
        lista.add(student4);
        lista.add(student5);

        for (Student s : lista) {
            System.out.println(s.toString());

        }
        Collections.sort(lista, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                return compareLong(student.getAge(),t1.getAge());
            }
            private int compareLong(int first, int second) {
                return (first < second) ? -1 : ((first == second) ? 0 : 1);
            }
        });


        for (Student s : lista) {
            System.out.println(s.toString());

        }


  }
  @Test
  public void test08(){
      String str="http://www.zrytech.com/NopShop/Mobile/TimeShow/TimeShowDetail/351";
      System.out.println("分隔数据"+str.lastIndexOf("/"));
      int lastl=str.lastIndexOf("/");
      System.out.println(str.substring(lastl+1));
  }
}
