package com.wxb.blog.common.base;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class ConfigItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name="key")
    private String key;

    @JSONField(name="value")
    private String value;

    @JSONField(name="decs")
    private String desc;

    @JSONField(name="level")
    private int level;

    /**
     * 更新时间
     */
    @JSONField(name="updtime",format="yyyy-MM-dd HH:mm:ss")
    private Date updtime;

    /**
     * 更新人
     */
    @JSONField(name="updname")
    private String updname;

    /**
     * 是否为表达式
     */
    @JSONField(name = "expression")
    private boolean expression;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getUpdtime() {
        return updtime;
    }

    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

    public String getUpdname() {
        return updname;
    }

    public void setUpdname(String updname) {
        this.updname = updname;
    }

    public boolean isExpression() {
        return expression;
    }

    public void setExpression(boolean expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ConfigItem that = (ConfigItem) o;

        if (level != that.level)
            return false;
        if (expression != that.expression)
            return false;
        if (key != null ? !key.equals(that.key) : that.key != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null)
            return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null)
            return false;
        if (updtime != null ? !updtime.equals(that.updtime) : that.updtime != null)
            return false;
        return updname != null ? updname.equals(that.updname) : that.updname == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + (updtime != null ? updtime.hashCode() : 0);
        result = 31 * result + (updname != null ? updname.hashCode() : 0);
        result = 31 * result + (expression ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConfigItem{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                ", level=" + level +
                ", updtime=" + updtime +
                ", updname='" + updname + '\'' +
                ", expression=" + expression +
                '}';
    }
}
