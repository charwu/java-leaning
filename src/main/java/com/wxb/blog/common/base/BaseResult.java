package com.wxb.blog.common.base;

import java.io.Serializable;

public class BaseResult implements Serializable {

    private static final long serialVersionUID = -4496867430298036980L;
    private String sid;
    private boolean success;
    private String code;
    private String description;

    public BaseResult() {
    }
    public String getSid() {
        return this.sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isFailed() {
        return !this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
