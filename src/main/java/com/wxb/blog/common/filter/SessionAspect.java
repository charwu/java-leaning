package com.wxb.blog.common.filter;


import com.wxb.blog.biz.user.UserBaseBiz;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.base.SessionDTO;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.common.utils.CookieUtil;
import com.wxb.blog.model.BaseForm;
import com.wxb.blog.model.SessionInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 验签及登录信息封装
 *
 * @author: xupu
 */
@Component
@Aspect
public class SessionAspect implements Ordered {
    private static final Logger logger = LoggerFactory.getLogger(SessionAspect.class);
    @Autowired
    private UserBaseBiz userBaseBiz;

    /**
     * 所有标注了@Auth标签的方法切入点
     */
    @Pointcut("@annotation(com.wxb.blog.common.filter.Auth)")
    public void aspect() {
    }

    @Around(value = "aspect()")
    public Object around(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            BaseForm form = (BaseForm) this.getTargetObject(joinPoint.getArgs(), BaseForm.class);
            MethodSignature sig = (MethodSignature) joinPoint.getSignature();
            Object obj = joinPoint.getTarget();
            Method method = AopUtils.getTargetClass(obj).getMethod(sig.getMethod().getName(), sig.getMethod().getParameterTypes());
            Auth auth = method.getAnnotation(Auth.class);
            if (request != null) {
                SessionDTO session = this.getSession(form);
                    if (session == null && auth.autoReturn()) {
                    throw new BusinessException(ErrorConstants.NO_LOGIN_ERROR_CODE, ErrorConstants.NO_LOGIN_ERROR_MESSAGE);
                } else {
                    this.setSession(session, form, request);
                }
            }

        }
        return ((ProceedingJoinPoint) joinPoint).proceed();
    }

    /**
     * 设置登录信息
     *
     * @param
     * @title 设置登录信息
     */
    private void setSession(SessionDTO session, BaseForm form, HttpServletRequest request) {
        if (form != null) {
            if (session != null) {
                SessionInfo sessionInfo = new SessionInfo();
                BeanUtils.copyProperties(session,sessionInfo);
                form.setSessionInfo(sessionInfo);
                form.setAuthenticated(true);
            }
        }
    }

    /**
     * 获取登录信息
     *
     * @param
     * @title 获取登录信息
     * @author xupu
     */
    private SessionDTO getSession(BaseForm form) throws Exception{

        String userkey = form.getUserkey();
        if (userkey == null) {
            return null;
        }
        return userBaseBiz.getSessionByUsekey(userkey);
    }

    /**
     * @param objects
     * @param clas
     * @return
     * @Description: 获取参数中目标对象
     * @return: Object
     * @author: xupu
     * @date: 2017年11月21日 上午10:21:19
     */
    private Object getTargetObject(Object[] objects, Class<?> clas) {
        if (objects != null && objects.length > 0) {
            for (Object object2 : objects) {
                if (object2 != null && clas.isAssignableFrom(object2.getClass())) {
                    return object2;
                }
            }
        }
        return null;
    }

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 4;
    }
}
