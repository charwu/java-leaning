package com.wxb.blog.model.vo.user;

import lombok.Data;

@Data
public class UserInfoVO {

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

    /** 性别 */
    private Byte sex;

    /** 个人简介 */
    private String summary;
}
