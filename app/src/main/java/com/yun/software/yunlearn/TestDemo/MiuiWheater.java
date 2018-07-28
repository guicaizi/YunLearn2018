package com.yun.software.yunlearn.TestDemo;

import android.app.Activity;
import android.widget.BaseAdapter;

import com.example.ccy.miuiweatherline.MiuiWeatherView;
import com.example.ccy.miuiweatherline.WeatherBean;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanliang
 * on 2018/7/24 16:29
 */

public class MiuiWheater extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.miuizhexian;
    }

    @Override
    public void setData() {
        MiuiWeatherView weatherView = (MiuiWeatherView) findViewById(R.id.weather);
        List<WeatherBean> data = new ArrayList<>();
        WeatherBean b2 = new WeatherBean(WeatherBean.CLOUDY,0,"05:00");
        WeatherBean b3 = new WeatherBean(WeatherBean.RAIN,10,"05:00");
        data.add(b2);
        data.add(b3);
        for (int i = 0; i < 30; i++) {
            int temp = (int) ( 100 * Math.random() );
            WeatherBean b1;
            if(i<10){
                b1 = new WeatherBean(WeatherBean.SUN,temp,"05:00");
            }else if(i<20){
                b1 = new WeatherBean(WeatherBean.SNOW,temp,"05:00");
            }else{
                b1 = new WeatherBean(WeatherBean.THUNDER,temp,"05:00");
            }

            data.add(b1);
        }
        //add your WeatherBean to data


        weatherView.setData(data);
    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
