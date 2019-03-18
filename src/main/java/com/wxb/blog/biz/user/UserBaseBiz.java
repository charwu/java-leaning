package com.wxb.blog.biz.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.base.SessionDTO;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.common.utils.AceUtil;
import com.wxb.blog.common.utils.DesUtil;
import com.wxb.blog.dao.bean.user.UserPO;
import com.wxb.blog.dao.mapper.user.UserPOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.Date;

/**
 * 用户基本信息获取，用于baseform信息
 *
 * @author xupu
 */
@Service
public class UserBaseBiz {

    private static final Logger logger = LoggerFactory.getLogger(UserBaseBiz.class);

    @Autowired
    private UserPOMapper userPOMapper;

    /**
     * @param userkey
     * @return SessionDTO
     * @title 通过userkey获取用户信息
     */
    public SessionDTO getSessionByUsekey(String userkey) throws Exception{
        // 判断userkey参数是否空
        if (StringUtils.isEmpty(userkey)) {
            return null;
        }

        userkey = URLDecoder.decode(userkey, "UTF-8");
        userkey = userkey.replace(" ","+");
        SessionDTO sessionDTO = new SessionDTO();

        String str = AceUtil.Decrypt(userkey);
        if(str != null){
            String[] strarray=str.split("\\|");
            if(strarray.length > 0){
                sessionDTO.setUserId(Integer.valueOf(strarray[0]));
                Long time =Long.parseLong(strarray[2]);
                Long now = new Date().getTime();
                if(time.compareTo(now) < 0){
                    return null;
                }
                UserPO userPO = getUserInfoByUserId(Integer.valueOf(strarray[0]));
                if(userPO != null){
                    if(userPO.getIsUse().intValue() == 2){
                        throw new BusinessException(ErrorConstants.USER_NOT_USED_CODE,ErrorConstants.USER_NOT_USED_MESSAGE);
                    }
                    BeanUtils.copyProperties(userPO,sessionDTO);
                }else {
                    throw new BusinessException(ErrorConstants.NO_MEMBER_SIGN_ERROR_CODE,ErrorConstants.NO_MEMBER_SIGN_ERROR_MESSAGE);
                }
            }else{
                return null;
            }
        }else{
            return null;
        }

        return sessionDTO;
    }

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    private UserPO getUserInfoByUserId(Integer userId){
        return userPOMapper.selectByPrimaryKey(userId);
    }

}
