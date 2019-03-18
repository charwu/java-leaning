package com.wxb.blog.controller.register;

import com.wxb.blog.biz.register.RegisterBiz;
import com.wxb.blog.common.utils.ResultUtil;
import com.wxb.blog.model.ResultVO;
import com.wxb.blog.model.form.register.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(allowCredentials = "true",origins = "http://recode.wuxiaob.com", maxAge = 3600)
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private RegisterBiz registerBiz;

    /**
     * 注册
     * @param form
     * @return
     */
    @RequestMapping(value = "/run")
    @ResponseBody
    public ResultVO<Boolean> register(@RequestBody RegisterForm form) throws Exception{
        Boolean isRight = registerBiz.register(form);
        return ResultUtil.createSuccessRes(isRight);
    }
}
