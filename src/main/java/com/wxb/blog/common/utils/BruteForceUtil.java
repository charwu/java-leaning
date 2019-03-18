package com.wxb.blog.common.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class BruteForceUtil {
    @Data
    public static class StringMap{
        private String value;
        private Integer type;
    }

    /**
     *
     * @param soure 字符串
     * @param rep 查找字符串
     * @return
     */
    public static  String bruteFore(String soure,String rep){
        if(StringUtils.isBlank(soure) || StringUtils.isBlank(rep)){
            return null;
        }
        List<StringMap> soureMap = getStringBrute(soure);
        List<StringMap> repMap = getStringBrute(rep);
        for (StringMap stringMap : repMap) {
            for (StringMap map : soureMap) {
                if((map.getValue().equalsIgnoreCase(stringMap.getValue())) && map.getType() == 0){
                    map.setValue("<hl>"+map.getValue()+"</hl>");
                    map.setType(1);
                }
            }
        }
        String str = "";
        for (StringMap stringMap : soureMap) {
            str = str + stringMap.getValue();
        }
        return str;
    }

    private static List<StringMap> getStringBrute(String str){
        List<StringMap> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i ++){
            StringMap stringMap = new StringMap();
            stringMap.setType(0);
            stringMap.setValue(String.valueOf(str.charAt(i)));
            list.add(stringMap);
        }
        return list;
    }
}
