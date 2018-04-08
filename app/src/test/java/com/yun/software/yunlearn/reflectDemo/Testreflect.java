package com.yun.software.yunlearn.reflectDemo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yanliang
 * on 2018/4/3 10:44
 */

public class Testreflect {
    @Test
    public void testflect() {
        try {
            Class c = Class.forName("com.yun.software.yunlearn.reflectDemo.People");
            //           Constructor[] cons=c.getDeclaredConstructors();
            //            for (Constructor con : cons) {
            //                System.out.println(con);
            //
            //            }
            try {
                Constructor con = c.getDeclaredConstructor(String.class, String.class);
                con.setAccessible(true);
                People people = (People) con.newInstance("zhansan", "29");
                people.showInfor();
                Field field = c.getDeclaredField("name");
                field.setAccessible(true);
                String name = (String) field.get(people);
                System.out.println(name);
                field.set(people, "张三");
                people.showInfor();
                Method method = c.getDeclaredMethod("showInforage");
                method.setAccessible(true);
                method.invoke(people);


                //                      SingInstanceParent singInstanceParent=new SingInstanceParent();
                //
                //                      Field field1= singInstanceParent.getClass().getDeclaredField("instanc");
                //                       SingInstanc  singInstanc=field1.get(null);


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取构造方法
        // public Constructor[] getConstructors():所有公共构造方法
        // public Constructor[] getDeclaredConstructors():所有构造方法
        // Constructor[] cons = c.getDeclaredConstructors();
        // for (Constructor con : cons) {
        // System.out.println(con);
        // }


    }


}
