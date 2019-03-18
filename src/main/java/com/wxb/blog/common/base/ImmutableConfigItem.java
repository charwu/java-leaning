package com.wxb.blog.common.base;

import java.math.BigDecimal;
import java.util.Date;

public class ImmutableConfigItem {
    /**
     * 内部可修改配置项
     */
    private ConfigItem configItem;

    public ImmutableConfigItem(ConfigItem configItem){
        this.configItem = configItem;
    }

    public String getKey() {
        return configItem.getKey();
    }

    public String getValue() {
        return configItem.getValue();
    }

    public Integer getIntegerValue(){
        return Integer.valueOf(configItem.getValue());
    }

    public Boolean getBooleanValue(){
        return Boolean.valueOf(configItem.getValue());
    }

    public Float getFloatValue(){
        return Float.valueOf(configItem.getValue());
    }

    public Long getLongValue(){
        return Long.valueOf(configItem.getValue());
    }

    public Double getDoubleValue(){
        return Double.valueOf(configItem.getValue());
    }

    public Short getShortValue(){
        return Short.valueOf(configItem.getValue());
    }

    public Byte getByteValue(){
        return Byte.valueOf(configItem.getValue());
    }

    public BigDecimal getBigDecimalValue(){
        return new BigDecimal(configItem.getValue());
    }

    public String getDesc() {
        return configItem.getDesc();
    }

    public int getLevel() {
        return configItem.getLevel();
    }

    public Date getUpdtime() {
        return configItem.getUpdtime();
    }

    public String getUpdname() {
        return configItem.getUpdname();
    }

    public boolean isExpression() {
        return configItem.isExpression();
    }
}
