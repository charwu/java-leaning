package com.wxb.blog.common.utils;

import com.alibaba.fastjson.JSON;

public class LogUtil {
    public static String callBackError(String clazz, Object object){
        return "Call back Error,"+ clazz +",param:" + JSON.toJSON(object);
    }
}
