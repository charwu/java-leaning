package com.wxb.blog.model.vo.content;

import lombok.Data;

import java.util.Date;

@Data
public class ContentVO {
    /** id */
    private Integer id;

    /** 标题 */
    private String title;

    /** 内容 */
    private String content;

    /** 用户id */
    private Integer uid;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 昵称 */
    private String nickName;

    /** 发布时间 */
    private String pubTime;
}
