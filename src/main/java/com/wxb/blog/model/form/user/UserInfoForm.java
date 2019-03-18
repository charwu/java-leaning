package com.wxb.blog.model.form.user;

import com.wxb.blog.model.BaseForm;
import lombok.Data;

@Data
public class UserInfoForm extends BaseForm {

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
