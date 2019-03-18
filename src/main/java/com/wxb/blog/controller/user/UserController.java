package com.wxb.blog.controller.user;

import com.wxb.blog.biz.user.UserBiz;
import com.wxb.blog.common.filter.Auth;
import com.wxb.blog.common.utils.ResultUtil;
import com.wxb.blog.model.BaseForm;
import com.wxb.blog.model.ResultVO;
import com.wxb.blog.model.form.user.UserInfoForm;
import com.wxb.blog.model.vo.user.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(allowCredentials = "true",origins = "http://recode.wuxiaob.com", maxAge = 3600)
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/run")
    @ResponseBody
    @Auth
    public ResultVO<UserInfoVO> getUserInfo(@RequestBody BaseForm form) throws Exception{
        UserInfoVO resultVO = userBiz.getUserInfoByUserId(form.getSessionInfo().getUserId());
        return ResultUtil.createSuccessRes(resultVO);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    @Auth
    public ResultVO<Boolean> update(@RequestBody UserInfoForm form) throws Exception{
        Boolean resultVO = userBiz.updateUserInfo(form);
        return ResultUtil.createSuccessRes(resultVO);
    }
}
