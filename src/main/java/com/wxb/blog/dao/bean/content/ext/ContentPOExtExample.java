package com.wxb.blog.dao.bean.content.ext;

import lombok.Data;

@Data
public class ContentPOExtExample {

    private static final long serialVersionUID = -6845122313443200967L;

    private Integer userId;

    private Integer id;

    private Integer pid;

    public ContentPOExtExample(Integer userId,Integer id,Integer pid){
        super();
        this.userId = userId;
        this.id = id;
        this.pid = pid;
    }
}