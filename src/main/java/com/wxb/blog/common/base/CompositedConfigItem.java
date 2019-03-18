package com.wxb.blog.common.base;

import org.apache.commons.lang.StringUtils;

public class CompositedConfigItem {
    private String key;

    /**
     * 本地配置
     */
    private ImmutableConfigItem localConfig;

    /**
     * 配置中心配置
     */
    private ImmutableConfigItem ccConfig;

    /**
     * 覆盖文件配置
     */
    private ImmutableConfigItem overwriteConfig;

    public CompositedConfigItem(String key,ImmutableConfigItem localConfig, ImmutableConfigItem ccConfig,
                                ImmutableConfigItem overwriteConfig) {
        this.localConfig = localConfig;
        this.ccConfig = ccConfig;
        this.overwriteConfig = overwriteConfig;
        this.key = key;
        if((localConfig != null && !StringUtils.equals(key,localConfig.getKey()))
                || (ccConfig != null && !StringUtils.equals(key,ccConfig.getKey()))
                || (overwriteConfig != null && !StringUtils.equals(key,overwriteConfig.getKey()))){
            throw new IllegalArgumentException("key miss matched. key:"+key);
        }
    }

    public ImmutableConfigItem getLocalConfig() {
        return localConfig;
    }

    public ImmutableConfigItem getCcConfig() {
        return ccConfig;
    }

    public ImmutableConfigItem getOverwriteConfig() {
        return overwriteConfig;
    }

    public ImmutableConfigItem getCurrentConfig(){
        if(overwriteConfig != null && overwriteConfig.getValue() != null){
            return overwriteConfig;
        }
        if(ccConfig != null && ccConfig.getValue() != null){
            return ccConfig;
        }
        if(localConfig != null && localConfig.getValue() != null){
            return localConfig;
        }
        return null;
    }

    public String getCurrentValue(){
        ImmutableConfigItem currentConfig = getCurrentConfig();
        if(currentConfig != null){
            return currentConfig.getValue();
        }
        return null;
    }

    public String getKey(){
        return key;
    }
}
