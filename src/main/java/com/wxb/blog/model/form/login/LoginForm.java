package com.wxb.blog.model.form.login;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    /**
     * 账号
     */
    @NotNull
    private String userName;

    /**
     * 账号
     */
    @NotNull
    private String password;


}
