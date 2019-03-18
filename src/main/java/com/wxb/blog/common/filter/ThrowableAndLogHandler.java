package com.wxb.blog.common.filter;


import com.alibaba.fastjson.JSON;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.model.ResultVO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 日志打印以及异常处理、调用接口耗时等
 * 
 * 2018年1月12日
 */
@Aspect
@Component
public class ThrowableAndLogHandler implements Ordered {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThrowableAndLogHandler.class);

	public ThrowableAndLogHandler() {
	}

	@Around(value = "joinPoint()")
	public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
		return this.handlerDubboMethod(joinPoint);
	}

	@Pointcut("execution(* com.wxb.blog.controller..*.*(..))")
	public void joinPoint() {
	}
	
	/**
	 * 调用dubbo接口
	 * 
	 * @param joinPoint
	 * @return
	 */
	
	private Object handlerDubboMethod(ProceedingJoinPoint joinPoint) {
		String name = null;
		long usedTime = 0;
		Object[] args = null;
		MethodSignature sig = null;
		try {
			args = joinPoint.getArgs();
			// 打印接口返回值
			sig = (MethodSignature) joinPoint.getSignature();
			// 如果是dubbo接口，则显示调用日志
			name = sig.toString();
			Object result;
			long startTime = System.currentTimeMillis();
			// 输出请求日志
			traceReqeustLog(name, args);
			if (args == null || args.length <= 0) {
				result = joinPoint.proceed();
			}
			else {
				result = joinPoint.proceed(args);
			}
			usedTime = System.currentTimeMillis() - startTime;
			// 输出响应日志
			traceResponseLog(name, result, usedTime);

			return result;
		}
		catch (BusinessException e) {
			Object resultVO = this.createExceptionResult(e.getCode(), e.getMessage(), sig);
			traceResponseLog(name, resultVO, usedTime);
			return resultVO;
		}
		catch (Throwable e) {
			traceErrorLog(name, args, e);
			Object resultVO = this.createExceptionResult(ErrorConstants.SYSTEM_ERROR, ErrorConstants.SYSTEM_MESSAGE, sig);
			traceResponseLog(name, resultVO, usedTime);
			return resultVO;
		}
	}

	/**
	 * 创建异常回应消息
	 *
	 * @param code
	 * @param msg
	 * @param signature
	 * @return
	 */
	private Object createExceptionResult(int code, String msg, MethodSignature signature) {
		if (null != signature) {
			ResultVO result = new ResultVO();
			result.setSuccess(false);
			result.setErrCode(code);
			result.setErrMsg(msg);
			return result;
		}

		return null;
	}

	/**
	 * 调用其他接口
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unused")
	private Object handlerOtherMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		Object result;
		if (null == args || args.length <= 0) {
			result = joinPoint.proceed();
		}
		else {
			result = joinPoint.proceed(joinPoint.getArgs());
		}
		return result;
	}

	/**
	 * 输出请求日志
	 * 
	 * @param methodName 方法名称
	 * @param args 请求参数
	 */
	private void traceReqeustLog(String methodName, Object[] args) {
		if (!LOGGER.isInfoEnabled()) {
			return;
		}
		String log = "\r\n【请求】方法： " + methodName;
		if (args != null && args.length > 0) {
			Object arg = args[0];
			if(arg instanceof String){
				log += " 请求参数：\r\n" + arg.toString();
			}else{
				log += " 请求参数：\r\n" + JSON.toJSONString(arg);
			}

		}

		LOGGER.info(log);
	}

	/**
	 * 输出响应日志
	 * 
	 * @param methodName 方法名称
	 * @param result 接口返回信息
	 * @param usedTime 使用时间
	 */
	private void traceResponseLog(String methodName, Object result, long usedTime) {
		if (!LOGGER.isInfoEnabled()) {
			return;
		}
		if (null == result) {
			LOGGER.info(String.format("\r\n【响应】 %s\r\n耗时:%d\r\n 无返回参数",
					methodName, usedTime));
		}
		else {
			if(result instanceof String){
				LOGGER.info(String.format("\r\n【响应】 %s\r\n耗时:%d\r\n 返回参数 %s",
						methodName, usedTime, result.toString()));
			}else{
				LOGGER.info(String.format("\r\n【响应】 %s\r\n耗时:%d\r\n 返回参数 %s",
						methodName, usedTime, JSON.toJSONString(result)));
			}

		}
	}

	/**
	 * 输出异常日志
	 * 
	 * @param methodName 方法名
	 * @param args 传入参数
	 * @param e 异常
	 */
	private void traceErrorLog(String methodName, Object[] args, Throwable e) {
		String msg = String.format("调用接口%s出错", methodName);
		if (null != args) {
			msg += String.format("，%n请求参数%s", JSON.toJSONString(args));
		}
		LOGGER.error(msg, e);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
