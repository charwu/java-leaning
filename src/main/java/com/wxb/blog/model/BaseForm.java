package com.wxb.blog.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class BaseForm implements Serializable {
	private static final long serialVersionUID = -8225264561426567521L;

	/**
	 * 当前用户信息
	 * 
	 * @NoFile
	 */
	private SessionInfo sessionInfo;
    /**
	 * ip地址
	 * 
	 * @NoFile
	 */
    private String ip;

	/**
	 * 用户登录验证
	 *
	 * @NoFile
	 */
	private String userkey;

    /**
	 * 是否登录
	 * 
	 * @NoFile
	 */
	private boolean isAuthenticated;

}
