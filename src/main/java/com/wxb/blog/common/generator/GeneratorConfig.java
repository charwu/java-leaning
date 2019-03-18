package com.wxb.blog.common.generator;

import lombok.Data;

import java.util.List;

/**
 * @author huadongnan
 * @title
 * @date 2018/3/20.
 * @since 1.0.0
 */
@Data
public class GeneratorConfig {

	/**
	 * jar包地址
	 */
	private String driver;

	/**
	 * 数据库地址
	 */
	private String dbUrl;

	/**
	 * 用户
	 */
	private String user;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 生成到到包
	 */
	private String pack;

	/**
	 * 模版地址,默认不设置，必须在resources下
	 */
	private String template;

	/**
	 * 表模版地址，默认不设置，必须在resources下
	 */
	private String tableTemplate;

	/**
	 * 主键key
	 */
	private List<String> key;
}
