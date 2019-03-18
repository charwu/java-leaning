package com.wxb.blog.model.vo.math;

import lombok.Data;

@Data
public class MathVO {
    /**
     * 表达式
     */
    private String formula ;

    /**
     * 答案
     */
    private Integer key;
}
