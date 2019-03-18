package com.wxb.blog.biz.register;

import com.wxb.blog.biz.user.UserBiz;
import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.common.utils.MD5Util;
import com.wxb.blog.dao.bean.user.UserPO;
import com.wxb.blog.model.form.register.RegisterForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Component
public class RegisterBiz {
    private static final Logger logger = LoggerFactory.getLogger(RegisterBiz.class);

    @Autowired
    private UserBiz userBiz;

    /**
     * 注册
     * @param form
     * @return
     * @throws Exception
     */
    public Boolean register(RegisterForm form) throws Exception{
        Boolean isRight = true;
        // 检查用户是否注册
        Boolean isRegister = userBiz.checkUserInfo(form.getUserName());
        if(isRegister){
            throw new BusinessException(ErrorConstants.PARAMS_ERROR,"该用户已注册");
        }
        UserPO po = new UserPO();
        po.setPassword(MD5Util.getMD5String(form.getPassword()));
        po.setUsername(form.getUserName());
        po.setNickName(this.setNickName());
        Boolean isDone = userBiz.saveUserInfo(po);
        if(!isDone){
            isRight = false;
        }
        return  isRight;
    }

    private String setNickName(){
        return "blog_" + MD5Util.getMD5String(String.valueOf(new Date().getTime())).substring(0,6);
    }

    public static void main(String[] arges){
        String nickName = "blog_" + MD5Util.getMD5String(String.valueOf(new Date().getTime())).substring(0,6);
        System.out.println(nickName);
    }
}
