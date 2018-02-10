package com.yun.software.yunlearn.MVPLear.Modle;

import java.util.List;

/**
 * Created by yanliang
 * on 2018/2/7 11:19
 */

public interface BaseLoding {

    void load(LodingLisener lisener);

    interface LodingLisener<T>{
        void complate(List<T> list);
    }




}
