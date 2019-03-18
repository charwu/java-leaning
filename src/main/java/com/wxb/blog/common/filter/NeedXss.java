package com.wxb.blog.common.filter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 该注解用于 {@link com.tprc.yanxuan.common.converter.XssMappingJacksonHttpMessageConverter}中,
 * 用于需要xss过滤的String字段
 * </pre>
 * 
 * @author hujianjun
 * @date 2016年4月2日
 */
@Target( ElementType.FIELD )
@Retention( RetentionPolicy.RUNTIME )
@Documented
public @interface NeedXss {

}
