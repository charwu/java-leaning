package com.wxb.blog.common.base;

public class ConfigChangedItem {

    private CompositedConfigItem configItem;

    private Type type;

    public enum Type {
        ADDED,
        CHANGED,
        REMOVED
    }

    public ConfigChangedItem(CompositedConfigItem configItem, Type type) {
        this.configItem = configItem;
        this.type = type;
    }

    public CompositedConfigItem getConfigItem() {
        return configItem;
    }

    public void setConfigItem(CompositedConfigItem configItem) {
        this.configItem = configItem;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ConfigChangedItem{" +
                "configItem=" + configItem +
                ", type=" + type +
                '}';
    }
}
