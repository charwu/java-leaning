package com.wxb.blog.common.filter;

/**
 * 用于spring mvc httpMessageConverter 过滤
 * 
 * @author hujianjun
 * @date 2016年4月2日
 */
public interface MessageConverterHandler<T, K> {

	/**
	 * 用于在httpMessageConverter read(..)方法完成之后调用
	 * <p>
	 * 1.可以对converter映射出的Object进行处理
	 * </p>
	 * 
	 * @author hujianjun
	 * @date 2016年4月2日
	 * @param obj
	 * @param type
	 * @return
	 */
	public Object readAfter(T obj, K type);
}
