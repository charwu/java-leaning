package com.wxb.blog.common.base;

import java.util.List;
import java.util.Map;

public class Result<T> extends BaseResult {

    /**
     * 返回数据，可为基本类型（包装类），可以为其它可序列化对象
     */
    private T data;

    public static <T> Result<T> create() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        return result;
    }

    public Result<T> success(){
        success(null);
        return this;
    }

    public Result<T> success(T data){
        this.setSuccess(true);
        this.data = data;
        return this;
    }

    public Result<T> fail(String code,String description){
        this.setSuccess(false);
        this.setCode(code);
        this.setDescription(description);
        return this;
    }

    public Result<T> fail(String code){
        fail(code, null);
        return this;
    }

    public Result<T> code(String code){
        this.setCode(code);
        return this;
    }

    public Result<T> description(String description){
        this.setDescription(description);
        return this;
    }

    public Result<T> sid(String sid){
        this.setSid(sid);
        return this;
    }

    public Result<T> data(T data){
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 是否成功返回并带回数据
     * @return
     */
    public boolean isFailedOrDataEmpty(){
        return isFailed() || isDataEmpty();
    }

    /**
     * 数据是否为空<br/>
     * 如果是对象,则判断是否为空（NULL）<br/>
     * 如果是字符串,则判断是为空串（NULL,""）<br/>
     * 如果是List对象,则判断是否为空列表（isEmpty)<br/>
     * 如果是Map对象,则判断是否为空Map(isEmpty)<br/>
     * 如果是Array数组，则判断是否为空数组(length == 0)
     * @return
     */
    public boolean isDataEmpty(){
        if(data == null){
            return true;
        }

        if(data instanceof String){
            String str = (String) data;
            return str.trim().equals("");
        }else if(data instanceof List){
            List list = (List) data;
            return list.isEmpty();
        }else if(data instanceof Map){
            Map<Object,Object> map = (Map<Object,Object>) data;
            return map.isEmpty();
        }else if(data instanceof Object[]){
            Object[] array = (Object[]) data;
            return array.length == 0;
        }
        return false;
    }
}
