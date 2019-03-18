package com.wxb.blog.common.constants;

/**
 * 常量定义
 * 
 * @author Administrator
 * 
 */
public class ErrorConstants {
	/**
	 * 服务内部异常
	 */
	public static final int SUCCESS_CODE = 0;
	public static final String SUCCESS_MESSAGE = "返回成功";
	/**
	 * 服务内部异常
	 */
	public static final int SYSTEM_ERROR = 999;
	public static final String SYSTEM_MESSAGE = "系统错误";

	/**
	 * android验签异常
	 */
	public static final int CHECK_SIGN_ERROR = 909;
	public static final String CHECK_SIGN_MESSAGE = "系统错误909";

	/**
	 * 调用服务化接口错误. 调用其他soa或thrift接口出错
	 */
	public static final int CALL_SERVER_ERROR = 1001;
	public static final String CALL_SERVER_MESSAGE = "服务化异常";
	/**
	 * 调用服务化的接口返回失败
	 */
	public static final int SERVER_ERROR_CODE = 1002;
	public static final String SERVER_ERROR_MESSAGE = "服务化失败";
	/**
	 * 调用三方接口出错 调用其他公司或平台接口出错
	 */
	public static final int CALL_OPEN_ERROR = 2001;
	public static final String CALL_OPEN_MESSAGE = "第三方服务异常";
	/**
	 * 请求参数不合法
	 */
	public static final int PARAMS_ERROR = 3001;
	public static final String PARAMS_ERROR_MESSAGE = "参数错误";
	/**
	 * 验证码错误
	 */
	public static final int VERIFY_ERROR_CODE = 3002;
	public static final String VERIFY_ERROR_MSG_MESSAGE = "验证码错误";
	/**
	 * 没有可用的数据 调用接口的参数找不到对应的合法数据，例如:通过用户id找不到用户信息
	 */
	public static final int NO_DATA_ERROR = 0;
	public static final String NO_DATA_MESSAGE = "没有可用数据";

	/**
	 * 没有依赖数据
	 */
	public static final int NO_RELY_DATA_ERROR = 4001;
	public static final String NO_RELY_DATA_MESSAGE = "没有依赖数据";
	/**
	 * 没有权限
	 */
	public static final int PERMISSION_ERROR = 5001;
	public static final String PERMISSION_MESSAGE = "该用户没有权限";
	
	/**
	 * 用户未登录
	 */
	public static final int NO_LOGIN_ERROR_CODE = 5002;
	public static final String NO_LOGIN_ERROR_MESSAGE = "用户未登录";
	/**
	 * 不在活动期间
	 */
	public static final int NO_ACTIVITY_ERROR_CODE = 5003;
	public static final String NO_ACTIVITY_ERROR_MESSAGE = "不在活动期间";
	/**
	 * 不在活动期间
	 */
	public static final int NO_SIGN_ERROR_CODE = 5004;
	public static final String NO_SIGN_ERROR_MESSAGE = "没有报名，不能参加有限抢";
	/**
	 * 店铺处罚已关店
	 */
	public static final int CLOSE_SHOP_ERROR_CODE = 5005;
	public static final String CLOSE_SHOP_ERROR_MESSAGE = "店铺处罚已关店";

	/**
	 * 用户未登录
	 */
	public static final int NO_MEMBER_SIGN_ERROR_CODE = 5006;
	public static final String NO_MEMBER_SIGN_ERROR_MESSAGE = "验签失败";

	/**
	 * 用户被禁用
	 */
	public static final int USER_NOT_USED_CODE = 5007;
	public static final String USER_NOT_USED_MESSAGE = "用户被禁用";

	/**
	 * 发送频率过快
	 */
	public static final int DO_FAST_CODE = 6001;
	public static final String DO_FAST_MESSAGE = "处理频率太快，1分钟一次";
	/**
	 * 发送频率达到上限
	 */
	public static final int DO_TOPLIMIT_CODE = 6002;
	public static final String DO_TOPLIMIT_MESSAGE = "当日请求次数已达上限";

	/**
	 * 安全校验不通过
	 */
	public static final int SAFE_ERR_CODE = 7001;
	public static final String SAFE_ERR_MESSAGE = "安全校验不通过";
}
