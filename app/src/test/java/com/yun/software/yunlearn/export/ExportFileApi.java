package com.yun.software.yunlearn.export;

/**
 * 导出数据文件（数据文件可能有，数据库，文本，XML，Excel...pdf.未来)
 */
public interface ExportFileApi {
    /**
     * 导出文件
     * @param data 需要导出的数据
     * @return 是否成功
     */
    public boolean export(String data);

//    public void p();

}
