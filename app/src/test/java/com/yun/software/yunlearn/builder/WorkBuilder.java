package com.yun.software.yunlearn.builder;

/**
 * Created by yanliang
 * on 2018/3/6 13:45
 */

public class WorkBuilder {
  RoomParams roomParams;

    public WorkBuilder() {
        this.roomParams =new RoomParams();
    }

    public WorkBuilder makeWindos(String windows){
        roomParams.windows=windows;
        return this;

    }
    public WorkBuilder makeFoolders(String foolders){
        roomParams.foolders=foolders;
        return this;
    }

    public Room builder(){
        Room room=new Room();
        room.appaly(roomParams.windows,roomParams.foolders);
        return room;
    }
    private class RoomParams{
       String windows;
       String foolders;
  }

}
