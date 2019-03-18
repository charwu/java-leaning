package com.wxb.blog.common.generator;

import java.util.Arrays;
import java.util.List;

/**
 * 一些常量值可以放在里面
 */
public class GeneralConstants {

	public static final List<String> IMGExt = Arrays.asList(new String[] { "jpg", "png", "gif", "bmp" });
	
	/**
	 * 电话正则表达式-中国移动
	 * */
	public static final String TEL_REG_YD = "1(34[0-8]|(3[5-9]|5[017-9]|8[278])\\d)\\d{7}";

	/**
	 * 电话正则表达式-中国联通
	 * */
	public static final String TEL_REG_LT = "1(3[0-2]|5[256]|8[56])\\d{8}";

	/**
	 * 电话正则表达式-中国电信
	 * */
	public static final String TEL_REG_DX = "1((33|53|8[09])[0-9]|349)\\d{7}";

	/**
	 * 电话正则表达式-400
	 * */
	public static final String TEL_REG_400 = "400(-)?\\d{3}(-)?\\d{4}";

	/**
	 * 元宝来源
	 * */
	public static final Integer GOLD_INGOT_SOURCE = 9;

	/**
	 * 元宝商户号
	 * */
	public static final String GOLD_INGOT_MERCHANT_CODE = "zhubi20180500012";

	/**
	 * 元宝商户名
	 * */
	public static final String GOLD_INGOT_MERCHANT_NAME = "天蓬人才云事业部";
    
}
