package com.yun.software.corelib.Tools;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具
 *
 * @author LiBing
 */
public class StringUtils {

    /**
     * 产生时间戳
     *
     * @return
     */
    public static String createTimestamp() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return timestamp;
    }
    public static String getText(View v) {
        String text = "";
        if (v instanceof EditText) {
            text = ((EditText) v).getText().toString().trim();
        } else if (v instanceof TextView) {
            text = ((TextView) v).getText().toString().trim();
        } else if (v instanceof Button) {
            text = ((Button) v).getText().toString().trim();
        }
        return text;
    }

    public static String getTagText(View v) {
        return v.getTag() != null ? v.getTag() + "" : "";
    }

    public static boolean getTagBoolean(View v) {
        return v.getTag() != null ? (boolean) v.getTag() : false;
    }


    public static String getEncripytion(String totalstr){

        StringBuffer buffer=new StringBuffer();
        buffer.append("Basic");
        buffer.append(" ");
        buffer.append(Base64.encodeToString(totalstr.getBytes(),Base64.DEFAULT));


        return buffer.toString();
    };

//    /**
//     * 产生接口验证参数
//     *
//     * @param timestamp
//     * @return
//     */
//    public static String createSign(String timestamp) {
//        String sign = "";
//        String toDigest = timestamp + RequestConstant.APP_SECRET;
//        sign = MD5.getMD5(toDigest);
//        return sign;
//    }
    /**
     * 将对象数据转换成Int
     *
     * @param obj
     * @return
     */
    public static int toInt(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        String str = String.valueOf(obj);
        return (int) Float.parseFloat(str);
    }
    /**
     * 将对象数据转换成String
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null || "".equals(obj) || "null".equals(obj)) {
            return "";
        }
        return String.valueOf(obj);
    }
    /**
     * 将对象类小数型转换成String 整型
     *
     * @param obj
     * @return
     */
    public static String toStringInt(Object obj) {
        return toString(toInt(obj));
    }

    /**
     * 将对象数据转换成Float
     *
     * @param obj
     * @return
     */
    public static float toFloat(Object obj) {
        if (obj == null || "".equals(obj)) {
            return 0;
        }
        String str = String.valueOf(obj);
        return Float.parseFloat(str);
    }
    public static double toDouble(String str) {

        return Double.parseDouble(str);
    }
    /**
     * 判断字符串是否null或为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {

        if (str == null || "".equals(str) || "null".equals(str)||"\"\"".equals(str)) {
            return true;
        }
        if (str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串不是null或为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断是否是11位手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(14[5,7])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        String regist= "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regist);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * str1 时间 比str2 时间大
     */
    public static boolean twoTimeCompress(String str1, String str2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Date date, date2;
        boolean bool = false;
        try {
            date = sdf.parse(str1);
            date2 = sdf.parse(str2);
//            long str = Math.max(date.getTime(), date2.getTime());
            long str =date.getTime()>=date2.getTime()?date.getTime():date2.getTime();
            if (str == date.getTime()) {
                bool = true;
                if (date.getTime() == date2.getTime()) {
                    bool = false;
                }
            } else {
                bool = false;
                if (date.getTime() == date2.getTime()) {
                    bool = true;
                }
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bool;
    }
    /**
     * 获取去掉前后空格后的string,为null则返回""
     *
     * @param s
     * @return
     */
    public static String getTrimedString(String s) {
        return getString(s).trim();
    }
    /**
     * 获取string,为null则返回""
     *
     * @param s
     * @return
     */
    public static String getString(String s) {
        return s == null ? "" : s;
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    /**
     * 按英文"."逗号分隔
     *
     * @param str
     * @return
     */
    public static String splitByComma(String str) {
        if (isEmpty(str)) {
            return null;
        }
        str.trim();
        // 1个或多个空格
        String[] arr = str.split("\\.");
        List<String> list = Arrays.asList(arr);
        for (int i = 0; i <list.size() ; i++) {
//            MyLogUtils.i("yan","name===="+list.get(i));
        }
        String name=list.get(list.size()-1);
        return name;
    }
    public static String replace(String str) {
        if (isEmpty(str)) {
            return null;
        }
        String str1=str.replace('.','_');
        return str1;
    }



    public  static String encodeHeadInfo( String totalstr ) {

        String headInfo=getEncripytion(totalstr);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        String str=stringBuffer.toString();
        return str.substring(0,str.length()-6);
    }
    public  static String encodeHeadInfo2( String headInfo ) {


        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        String str=stringBuffer.toString();
        return str.substring(0,str.length()-6);
    }
    public  static String encodeHeadInfo3( String headInfo ) {


        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, length = headInfo.length(); i < length; i++) {
            char c = headInfo.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                stringBuffer.append( String.format ("\\u%04x", (int)c) );
            } else {
                stringBuffer.append(c);
            }
        }
        String str=stringBuffer.toString();
        return str;
    }
    /**
     * 读取baseurl
     * @param url
     * @return
     */
    public static String getBasUrl(String url) {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1) {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1) {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    public static String stampToDateNOtime(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String passowrdRegist(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]{6,20}$";
        //①构造一个模式.
        Pattern p = Pattern.compile(regEx);
        Pattern pA = Pattern.compile("^[A-Z]{6,20}$");
        Pattern pa = Pattern.compile("^[a-z]{6,20}$");
        Pattern pNum = Pattern.compile("^[0-9]{6,20}$");
        //②建造一个匹配器
        Matcher m = p.matcher(str);
        Matcher m1 = pA.matcher(str);
        Matcher m2 = pa.matcher(str);
        Matcher m3 = pNum.matcher(str);
        //大小写字母，数字，特殊字符 全集匹配（只要字符串匹配其中任何一个或多个都可以）
        String reg = "([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$";
        Pattern pAll = Pattern.compile(reg);
        Matcher mAll = pAll.matcher(str);
        //③进行判断，得到结果

        //因为字符串str如果匹配一个就不可能匹配其他的，具有互异性。还要排除都不匹配的情况，不满足这四项的字符
        if (m.matches() || m1.matches() || m2.matches() || m3.matches()) {
            return "数字,字母,标点符号至少包含两种！";
        } else if (!mAll.matches()) {
            return "请输入6-20位字符";
        } else {
            return "通过";
        }
    }
}
