package com.wxb.blog.common.base;

import java.util.List;
import com.google.common.collect.Lists;

public class ConfigDiff {
    /**
     * 新添加的配置列表,configItem为新添加的配置KV
     */
    private List<CompositedConfigItem> newList = Lists.newLinkedList();

    /**
     * 已经改变的配置列表，configItem为改变过后的配置KV，此时的KV为新值KV
     */
    private List<CompositedConfigItem> changedList = Lists.newLinkedList();

    /**
     * 已经被移除的配置列表，configItem为被移除的配置KV
     */
    private List<CompositedConfigItem> removedList = Lists.newLinkedList();

    public List<CompositedConfigItem> getNewList() {
        return newList;
    }

    public void setNewList(List<CompositedConfigItem> newList) {
        this.newList = newList;
    }

    public void addNew(CompositedConfigItem configItem){
        this.newList.add(configItem);
    }

    public List<CompositedConfigItem> getChangedList() {
        return changedList;
    }

    public void setChangedList(List<CompositedConfigItem> changedList) {
        this.changedList = changedList;
    }

    public void addChanged(CompositedConfigItem configItem){
        this.changedList.add(configItem);
    }

    public List<CompositedConfigItem> getRemovedList() {
        return removedList;
    }

    public void setRemovedList(List<CompositedConfigItem> removedList) {
        this.removedList = removedList;
    }

    public void addRemoved(CompositedConfigItem configItem){
        this.removedList.add(configItem);
    }

    public boolean isChanged(String key) {

        for(CompositedConfigItem item : changedList){
            if(item.getKey().equals(key)){
                return true;
            }
        }
        for(CompositedConfigItem item : newList){
            if(item.getKey().equals(key)){
                return true;
            }
        }
        for(CompositedConfigItem item : removedList){
            if(item.getKey().equals(key)){
                return true;
            }
        }

        return false;

    }

    public ConfigChangedItem getChanged(String key){

        ConfigChangedItem.Type type = null;
        CompositedConfigItem configItem = null;

        for(CompositedConfigItem item : changedList){
            if(item.getKey().equals(key)){
                configItem = item;
                type = ConfigChangedItem.Type.CHANGED;
            }
        }

        if(configItem == null){
            for(CompositedConfigItem item : newList){
                if(item.getKey().equals(key)){
                    configItem = item;
                    type = ConfigChangedItem.Type.ADDED;
                }
            }
        }


        if(configItem == null) {
            for (CompositedConfigItem item : removedList) {
                if (item.getKey().equals(key)) {
                    configItem = item;
                    type = ConfigChangedItem.Type.REMOVED;
                }
            }
        }

        if(configItem == null){
            return null;
        }

        return new ConfigChangedItem(configItem,type);
    }

    public boolean hasDiff(){
        if(newList != null && !newList.isEmpty()){
            return true;
        }
        if(changedList != null && !changedList.isEmpty()){
            return true;
        }
        if(removedList != null && !removedList.isEmpty()){
            return true;
        }
        return false;
    }
}
