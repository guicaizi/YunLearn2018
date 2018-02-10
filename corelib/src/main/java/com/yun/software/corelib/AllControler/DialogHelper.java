package com.yun.software.corelib.AllControler;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Window;

import com.yun.software.corelib.widget.UIAlertView;


/**
 * Created by yanliang
 * on 2017/10/13 15:57
 */

public class DialogHelper {


    private static DialogHelper instance=new DialogHelper();

    public static DialogHelper getInstance(){
            return instance;
        }


    public static UIAlertView showDelDialog(Context mcontext, String content, UIAlertView.ClickListenerInterface clickListenerInterface) {
        final UIAlertView delDialog = new UIAlertView(mcontext,  "温馨提示",content,
                "确定", "放弃");
        delDialog.show();
        delDialog.setClicklistener(clickListenerInterface);
        return delDialog;
    }
    public static  void dialogCoustom(Context context,int layoutResID){
        final AlertDialog dlgShareRegulation = new AlertDialog.Builder(context).create();
        dlgShareRegulation.show();
        Window window = dlgShareRegulation.getWindow();
        //获取对话框布局
        window.setContentView(layoutResID);
        //获取布局中的控件
        //取消
//        Button btn_cancle = (Button) window.findViewById(R.id.btn_cancle);
//        btn_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dlgShareRegulation.dismiss();
//            }
//        });
//        //确定
//        TextView btn_sure = (Button) window.findViewById(R.id.btn_sure);
//        btn_sure.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                dlgShareRegulation.dismiss();
//                Bundle bundle = new Bundle();
//                bundle.putString("isbind", "0");//未绑定跳去绑定
////                enterPage(BindAndUnbindPhoneOfMyCenterActivity.class, bundle);
//            }
//        });
    }
}
