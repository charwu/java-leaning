package com.wxb.blog.common.base;

public interface IConfigChangedListener extends Comparable<IConfigChangedListener> {
    void onChanged(ConfigChangedEvent var1);

    int getOrder();
}