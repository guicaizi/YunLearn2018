package com.yun.software.yunlearn.builder;

/**
 * Created by yanliang
 * on 2018/3/6 13:43
 */

public class Room {

       private String foolder;
       private String window;
       public void appaly(String window,String foolder){
           this.foolder=foolder;
           this.window=window;
       }


    @Override
    public String toString() {
        return "Room{" +
                "foolder='" + foolder + '\'' +
                ", window='" + window + '\'' +
                '}';
    }
}
