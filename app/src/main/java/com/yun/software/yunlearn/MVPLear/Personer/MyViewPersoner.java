package com.yun.software.yunlearn.MVPLear.Personer;

import com.yun.software.yunlearn.MVPLear.Modle.BaseLoding;
import com.yun.software.yunlearn.MVPLear.View.IyunLearnView;

import java.util.List;

/**
 * Created by yanliang
 * on 2018/2/7 11:21
 */

public class MyViewPersoner<T extends IyunLearnView> extends BasePersonner<T> {

    @Override
    public void dateLoding() {
        mview=mViewRefrence.get();
        if(mview!=null){
            mview.showLoding();
            loding.load(new BaseLoding.LodingLisener<String>() {


                @Override
                public void complate(List<String> list) {
                    if(mview!=null){
                        mview.showDate(list);
                    }
                }
            });

        }
    }
}
