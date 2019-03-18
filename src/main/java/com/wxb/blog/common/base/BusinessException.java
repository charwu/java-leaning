package com.wxb.blog.common.base;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BusinessException extends Exception{
    private static final long serialVersionUID = 1L;
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    /** @deprecated */
    @Deprecated
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    /** @deprecated */
    @Deprecated
    public void printStackTrace(PrintStream s) {
        System.out.println(String.format("业务异常:%d %s", this.code, super.getMessage()));
    }

    /** @deprecated */
    @Deprecated
    public void printStackTrace(PrintWriter s) {
        System.out.println(String.format("业务异常:%d %s", this.code, super.getMessage()));
    }
}
