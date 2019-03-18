package com.wxb.blog.biz.login;

import com.wxb.blog.biz.user.UserBiz;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.common.utils.AceUtil;
import com.wxb.blog.dao.bean.user.UserPO;
import com.wxb.blog.model.form.login.LoginForm;
import com.wxb.blog.model.vo.login.LoginResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Component
public class LoginBiz {
    private static final Logger logger = LoggerFactory.getLogger(LoginBiz.class);

    @Autowired
    private UserBiz userBiz;

    /**
     * 登录
     * @param form
     * @return
     * @throws Exception
     */
    public LoginResultVO userLogin(LoginForm form) throws Exception{
        LoginResultVO resultVO = new LoginResultVO();
        UserPO po = new UserPO();
        po.setUsername(form.getUserName());
        po.setPassword(form.getPassword());
        UserPO info = userBiz.getUserInfo(po);
        if(info != null && info.getUid() > 0){
            resultVO.setUserId(info.getUid());
            resultVO.setUserName(info.getUsername());
            Long tokenTime = new Date().getTime();
            tokenTime = tokenTime + 5*24*3600*1000;
            String str = info.getUid()+"|"+info.getUsername()+"|"+tokenTime;
            String sign = AceUtil.Encrypt(str);
            resultVO.setUserKey(sign);
        }else{
            throw new BusinessException(ErrorConstants.PARAMS_ERROR,"账号/密码错误");
        }
        return resultVO;
    }
}
