package com.wxb.blog.common.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @title 认证用户信息注解
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface Auth {
    /**
     * 未登录是否直接返回
     * */
    public boolean autoReturn() default true;
}
