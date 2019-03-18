package com.wxb.blog.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Description: cookie操作工具类
 * @author: wujun
 * @date: 2017年10月23日 下午4:00:04
 */
public class CookieUtil {

	/**
	 * 从request里面获取指定名字的cookie
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public static String getCookieValue( String name , HttpServletRequest request ) {
		Cookie[] cookies = request.getCookies();
		if ( cookies == null ) {
			return null;
		}

		for ( Cookie cookie : cookies ) {
			if ( cookie.getName().equals( name ) ) {
				return cookie.getValue();
			}
		}

		return null;
	}

	/**
	 * 从request里面获取指定名字的cookie(倒序)
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public static String getCookieValueDesc(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (int i = cookies.length - 1; i >= 0; i--) {
			if (cookies[i].getName().equals(name)) {
				return cookies[i].getValue();
			}
		}
		return null;
	}

}
