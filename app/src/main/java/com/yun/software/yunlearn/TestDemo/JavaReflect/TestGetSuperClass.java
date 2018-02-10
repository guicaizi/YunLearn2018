package com.yun.software.yunlearn.TestDemo.JavaReflect;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.yun.software.corelib.Tools.MyLogUtils;
import com.yun.software.corelib.base.BaseActivity;
import com.yun.software.yunlearn.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.Bind;

/**
 * Created by yanliang
 * on 2018/1/11 17:24
 */

public class TestGetSuperClass extends BaseActivity {


    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.button4)
    Button button4;
    @Bind(R.id.button3)
    Button button3;

    @Override
    public int getLayoutId() {
        return R.layout.test_super_class;
    }

    @Override
    public void setData() {
        Student stu=new Student();

        Class clazz=stu.getClass();

        MyLogUtils.i(clazz.toString());

        Type type = stu.getClass().getGenericSuperclass();
//
//        ParameterizedType pt= (ParameterizedType) (stu.getClass() .getGenericSuperclass());
//        Type[] ts=pt.getActualTypeArguments();
//        MyLogUtils.i("大小"+ts.length);


//        MyLogUtils.i(type.toString());

        // 强转为“参数化类型”

        //ParameterizedType参数化类型，即泛型
        if(type instanceof ParameterizedType){

            MyLogUtils.i("是泛型");
            ParameterizedType pt = (ParameterizedType) type; // BaseDao

            // 获取参数化类型中，实际类型的定义

            Type[] ts = pt.getActualTypeArguments();

            // 转换

            Class c= (Class) ts[0];
            Class c2= (Class) ts[1];

            MyLogUtils.i(c.toString());
            MyLogUtils.i(c2.toString());

        }else{
            MyLogUtils.i("不是是泛型");
        }



    }

    @Override
    public void addLisener() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }

}
