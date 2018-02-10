
package com.yun.software.corelib.base;

import android.os.Bundle;
import android.os.Message;

public interface commenBase {


    void handleUiMessage(Message msg);

    void enterPage(Class<?> clazz);

    void enterPage(Class<?> clazz, Bundle bundle);

    void enterPageForResult(Class<?> clazz, int requestCode);

    void enterPageForResult(Class<?> clazz, Bundle bundle, int requestCode);
    int getLayoutId();
    void setData();
    void addLisener();
}
