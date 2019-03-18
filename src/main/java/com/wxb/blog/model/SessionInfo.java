package com.wxb.blog.model;

import lombok.Data;

/**
 * @author wuxiaobing
 */
@Data
public class SessionInfo {

    /**  */
    private Integer userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickName;

    /** 生日 */
    private String birthday;

    /** 头像 */
    private String headUrl;

    /** 是否启用用户 1：启用  2：禁用 */
    private Byte isUse;

}
