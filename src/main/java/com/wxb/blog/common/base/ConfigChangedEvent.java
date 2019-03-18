package com.wxb.blog.common.base;

import java.util.Map;

public class ConfigChangedEvent {
    private ConfigDiff configDiff;
    private Map<String, CompositedConfigItem> oldConfigItemMap;

    public ConfigChangedEvent() {
    }

    public boolean isChanged(String key) {
        return this.configDiff.isChanged(key);
    }

    public ConfigChangedItem getChanged(String key) {
        return this.configDiff.getChanged(key);
    }

    public ConfigDiff getConfigDiff() {
        return this.configDiff;
    }

    public void setConfigDiff(ConfigDiff configDiff) {
        this.configDiff = configDiff;
    }

    public Map<String, CompositedConfigItem> getOldConfigItemMap() {
        return this.oldConfigItemMap;
    }

    public void setOldConfigItemMap(Map<String, CompositedConfigItem> oldConfigItemMap) {
        this.oldConfigItemMap = oldConfigItemMap;
    }
}
