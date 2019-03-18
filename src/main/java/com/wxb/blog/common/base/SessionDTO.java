package com.wxb.blog.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class SessionDTO implements Serializable {
    /**  */
    private Integer userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickName;

    /** 用户密码 */
    private String password;

    /** 生日 */
    private String birthday;

    /** 头像 */
    private String headUrl;

    /** 是否启用用户 1：启用  2：禁用 */
    private Byte isUse;
}
