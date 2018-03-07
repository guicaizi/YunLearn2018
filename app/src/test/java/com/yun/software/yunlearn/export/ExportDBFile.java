package com.yun.software.yunlearn.export;

/**
 * Created by 48608 on 2018/2/28.
 */

public class ExportDBFile implements ExportFileApi{
    @Override
    public boolean export(String data) {
        System.out.println("数据："+data);
        System.out.println("导出生成了数据库文件");
        return true;
    }
}
