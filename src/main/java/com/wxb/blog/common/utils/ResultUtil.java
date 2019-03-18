package com.wxb.blog.common.utils;


import com.wxb.blog.common.base.BaseResult;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.model.ResultVO;
import org.slf4j.Logger;

/**
 * 返回值工具类
 */
public class ResultUtil {
	
    /**
     * 创建错误信息返回值
     * @author guopeng
     * @param errCode 错误码
     * @param errMsg 错误描述
     * @return ResultVO
     */
    public static <T> ResultVO<T> createErrorRes(Integer errCode, String errMsg) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setErrCode(errCode);
        resultVO.setErrMsg(errMsg);
        resultVO.setSuccess(false);
        return resultVO;
    }
    /**
     * 创建错误信息返回值
     * @author guopeng
     * @param errCode 错误码
     * @param errMsg 错误描述
     * @return ResultVO
     */
    public static <T> ResultVO<T> createSysErrorRes() {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setErrCode(ErrorConstants.SYSTEM_ERROR);
        resultVO.setErrMsg(ErrorConstants.SYSTEM_MESSAGE);
        resultVO.setSuccess(false);
        return resultVO;
    }
    /**
   * 创建错误信息返回值
   * @author guopeng
   * @param e 业务异常
   * @return ResultVO
   */
    public static <T> ResultVO<T> createErrorRes(BusinessException e) {
      ResultVO<T> resultVO = new ResultVO<T>();
      resultVO.setErrCode(e.getCode());
      resultVO.setErrMsg(e.getMessage());
      resultVO.setSuccess(false);
      return resultVO;
    }
    /**
     * 创建成功返回值
     * @author guopeng
     * @param data 返回数据
     * @return ResultVO
     */
    public static <T> ResultVO<T> createSuccessRes(T data) {
        ResultVO<T> resultVO = new ResultVO<T>();
        resultVO.setData(data);
        resultVO.setSuccess(true);
        return resultVO;
    }
    
    public static void callBackCheck(BaseResult result, Logger logger, String clazz, Object object) throws BusinessException {
		if (result == null) {
			logger.info(LogUtil.callBackError(clazz, object));
			throw new BusinessException(ErrorConstants.CALL_SERVER_ERROR, ErrorConstants.CALL_SERVER_MESSAGE);
		} else if (!result.isSuccess()) {
			logger.info(LogUtil.callBackError(clazz, object));
			throw new BusinessException(ErrorConstants.CALL_SERVER_ERROR, result.getDescription());
		}
    }
    
}
