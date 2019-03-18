package com.wxb.blog.controller.login;


import com.wxb.blog.biz.login.LoginBiz;
import com.wxb.blog.common.utils.ResultUtil;
import com.wxb.blog.model.ResultVO;
import com.wxb.blog.model.form.login.LoginForm;
import com.wxb.blog.model.vo.login.LoginResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
@CrossOrigin(allowCredentials = "true",origins = "http://recode.wuxiaob.com", maxAge = 3600)
public class LoginController {
    @Autowired
    private LoginBiz loginBiz;

    @RequestMapping(value = "/logindo")
    @ResponseBody
    public ResultVO<LoginResultVO> loginDo(@RequestBody LoginForm form) throws Exception{
        LoginResultVO resultVO = loginBiz.userLogin(form);
        return ResultUtil.createSuccessRes(resultVO);
    }
}
