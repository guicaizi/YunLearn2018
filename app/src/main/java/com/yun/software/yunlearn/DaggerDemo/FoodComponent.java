package com.yun.software.yunlearn.DaggerDemo;

import dagger.Component;

/**
 * Created by yanliang
 * on 2018/3/7 15:35
 */

@Component
public interface FoodComponent {
    void inject(Dagger2TestDemo mainActivity);
}