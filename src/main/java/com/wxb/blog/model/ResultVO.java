package com.wxb.blog.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 
 * @ClassName: ResultVO 
 * @Description: 共用返回数据类
 * @param <T>
 */
@Data
public class ResultVO<T> implements Serializable {

	/**
	 * 通用返回数据类
	 */
	private static final long serialVersionUID = 6179171998689596874L;
	
	/**
	 * 0 成功，其他失败
	 */
	private int errCode;

    private String errMsg;

	/** 成功标志*/
	private boolean success;

    private T data;

    public ResultVO() {
        errCode = 0;
        errMsg = "返回成功";
    }
    public ResultVO(int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public ResultVO(int errCode, String errMsg, T data) {
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.data = data;
	}

}
