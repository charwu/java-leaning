package com.wxb.blog.model.vo.login;

import lombok.Data;

@Data
public class LoginResultVO {
    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户key
     */
    private String userKey;

}
