package com.wxb.blog.common.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSR303CheckException extends IllegalArgumentException{
    private static final long serialVersionUID = 1L;
    private Map<String, String> errorMap = new HashMap();
    private String msg;

    public JSR303CheckException() {
    }

    public JSR303CheckException(Throwable cause) {
        super(cause);
    }

    public Map<String, String> getErrorMap() {
        return this.errorMap;
    }

    public void addError(String parameter, String msg) {
        this.errorMap.put(parameter, msg);
        this.msg = null;
    }

    public String getMessage() {
        if (this.msg == null) {
            if (this.errorMap.isEmpty()) {
                this.msg = "";
            } else {
                StringBuilder sb = new StringBuilder();
                Iterator var2 = this.errorMap.entrySet().iterator();

                while(var2.hasNext()) {
                    Map.Entry entry = (Map.Entry)var2.next();
                    sb.append(entry.getKey()).append(",").append(entry.getValue()).append(",");
                }

                sb.deleteCharAt(sb.length() - 1);
                this.msg = sb.toString();
            }
        }

        return this.msg;
    }

    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
