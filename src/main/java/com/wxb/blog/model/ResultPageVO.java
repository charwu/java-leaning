package com.wxb.blog.model;

import lombok.Data;

@Data
public class ResultPageVO {
    /**
     * 当前页码
     */
    private Integer currentPage = 0;

    /**
     * 总数
     */
    private Integer total = 0;

    /**
     * 大小
     */
    private Integer pageSize = 0;

    /**
     * 总页数
     */
    private Integer totalPage = 0;
}
