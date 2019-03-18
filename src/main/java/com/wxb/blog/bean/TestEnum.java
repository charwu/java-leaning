package com.wxb.blog.bean;

public enum  TestEnum {
    ELM("ELM","#1#");

    private final String key;
    private final String value;

    TestEnum(String key,String value){
        this.key = key;
        this.value = value;
    }
    public static String getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(TestEnum temp:TestEnum.values()){
            if(temp.getKey().equals(key)){
                return temp.getValue();
            }
        }
        return null;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
