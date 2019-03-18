package com.wxb.blog.common.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 用于不需要xss过滤的String字段，一般该字段的值为json字符，不需要xss处理，因为json字符串经过xss处理之后会出现格式错误（或者说不会出现格式错误，是处理方式问题）
 * </pre>
 * 
 * @author hujianjun
 * @date 2016年4月2日
 */
@Target( ElementType.FIELD )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface NotXss {

}
