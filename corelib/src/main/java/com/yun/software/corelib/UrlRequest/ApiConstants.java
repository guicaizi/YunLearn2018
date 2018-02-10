package com.yun.software.corelib.UrlRequest;

/**
 * Created by yanliang
 * on 2017/11/24 11:39
 */

public class ApiConstants {
    /**
     *token 默认配置 
     */  
    public static String Authorization = "";
    /**
     *固定appid  请求头用到
     */  
    public static final String appid = "8619c3128f75420b8bd9f05b2ec97aea";
    /**
     *服务器地址
     */  
    public static final String INTERFACE_ADDRESS = "http://119.23.172.36";
    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            /**
             * 登录
             */
            case HostType.LEARN_LOGIN:
                host = INTERFACE_ADDRESS + ":8080";
                break;
            /**
             * 学习
             */
            case HostType.LEARN_SERVER:
                host = INTERFACE_ADDRESS + ":8084";
                break;
            /**
             * 培训
             */
            case HostType.LEARN_PEIXUN:
                host = INTERFACE_ADDRESS + ":8083";
                break;
            /**
             * 公告
             */
            case HostType.LEARN_POST:
                host = INTERFACE_ADDRESS + ":8081";
                break;
            /**
             * 考试
             */
            case HostType.LEARN_TEST:
                host = INTERFACE_ADDRESS + ":8082";
                break;
            /**
             * 首页
             */
            case HostType.LEARN_MAIN:
                host = INTERFACE_ADDRESS + ":8085";
                break;
            /**
             * 个人中心
             */
            case HostType.LEARN_CENTER:
                host = INTERFACE_ADDRESS + ":8087";
                break;
            /**
             * 下载
             */
            case HostType.LEARN_UPLOAD:
                host = INTERFACE_ADDRESS + ":8086";
                break;
            default:
                host = "";
                break;
        }
        return host;
    }


}
