package com.wxb.blog.model.form.register;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterForm {
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
