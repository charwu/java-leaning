package com.wxb.blog.common;

import com.wxb.blog.common.base.BaseResult;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.utils.LogUtil;
import com.wxb.blog.common.base.Result;
import com.wxb.blog.common.base.ServerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 结果转换
 */
public class ResultBuild {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultBuild.class);

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ServerConstant.Error.SUCCESS + "");
        result.setDescription("成功");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setSuccess(true);
        result.setCode(ServerConstant.Error.SUCCESS + "");
        result.setDescription("成功");
        return result;
    }

    public static <T> Result<T> exception(BusinessException e) {
    	Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(e.getCode() + "");
        result.setDescription(e.getMessage());
        return result;
    }

    public static <T> Result<T> exception(IllegalArgumentException e) {
    	Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ServerConstant.Error.PARAMS_INAVAILABLE + "");
        result.setDescription(e.getMessage());
        LOGGER.error("param error ! ", e);
        return result;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
    	Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(code + "");
        result.setDescription(msg);
        return result;
    }

    public static <T> Result<T> paramError(String description) {
    	Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ServerConstant.Error.PARAMS_INAVAILABLE + "");
        result.setDescription(description);
        return result;
    }

    public static <T> Result<T> exception(Exception e) {
    	Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ServerConstant.Error.SYSTEM_INNER_ERROR + "");
        result.setDescription(e.getMessage());
        return result;
    }

    public static void callBackCheck(BaseResult result, Logger logger, String clazz, Object object) throws BusinessException {
		if (result == null) {
			logger.info(LogUtil.callBackError(clazz, object));
			throw new BusinessException(ServerConstant.Error.CALL_SERVER_INTERFACE_FAIL, "服务化异常");
		} else if (!result.isSuccess()) {
			logger.info(LogUtil.callBackError(clazz, object));
			throw new BusinessException(ServerConstant.Error.CALL_SERVER_INTERFACE_FAIL, result.getDescription());
		}
    }
}
