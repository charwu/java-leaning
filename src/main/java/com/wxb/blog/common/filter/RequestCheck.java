package com.wxb.blog.common.filter;


import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.base.JSR303CheckException;
import com.wxb.blog.common.base.JSR303Checker;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.model.BaseForm;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author xupu
 * @title
 * @date 2018/5/8.
 * @since 1.0.0
 */
@Component
@Aspect
public class RequestCheck implements Ordered {

	@Pointcut("execution(* com.wxb.blog.controller. .*.*(..))")
	public void aspect() {
	}

	@Around(value = "aspect()")
	public Object check(ProceedingJoinPoint point) throws Throwable {
		BaseForm request = null;
		for (Object object : point.getArgs()) {
			if (object instanceof BaseForm) {
				request = (BaseForm) object;
				break;
			}
		}

		try {
			if (request != null) {
				JSR303Checker.check(request);
			}

		} catch (JSR303CheckException e) {
			throw new BusinessException(ErrorConstants.PARAMS_ERROR, e.getMessage());
		}
		return point.proceed();
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 2;
	}
}
