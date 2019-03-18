package com.wxb.blog.dao.bean.content.ext;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @author wxb
 * @date 2018-12-12
 *
 */
@Data
public class ContentPOExt implements Serializable {
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
}