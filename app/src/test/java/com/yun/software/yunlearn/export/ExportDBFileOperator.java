package com.yun.software.yunlearn.export;

/**
 * Created by 48608 on 2018/2/28.
 */

public class ExportDBFileOperator extends ExportOperator{
    @Override
    public ExportFileApi factoryMethod() {
        return new ExportDBFile();
    }
}
