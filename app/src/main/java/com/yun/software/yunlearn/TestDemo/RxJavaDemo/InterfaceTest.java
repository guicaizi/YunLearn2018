package com.yun.software.yunlearn.TestDemo.RxJavaDemo;

/**
 * Created by yanliang
 * on 2018/2/8 16:22
 */

public class InterfaceTest {

    public   static void ceshi (){
             A a=new My();
             Inner inner=new Inner() {
                 @Override
                 public void hello() {

                 }
             };

    }

}
  interface A{
    void a();
    void c();
  }
  interface B{
    public  int a=10;
    void b();
    void c();

  }
 class My implements A,B{

      @Override
      public void a() {
          int b=a+20;
      }

      @Override
      public void b() {

      }

      @Override
      public void c() {

      }


  }
    abstract class  Inner{
    public abstract void hello();
  }
