package com.yun.software.yunlearn.EventBus;

/**
 *
 * @author flymegoc
 * @date 2018/1/22
 */

public class LowMemoryEvent {
    private String tag;

    public LowMemoryEvent(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
