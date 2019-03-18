package com.wxb.blog.model.form.math;

import lombok.Data;

@Data
public class MathForm {
    /**
     * 开始数
     */
    private Integer start = 0;

    /**
     * 截止数
     */
    private Integer end = 100;

    /**
     * 题目数
     */
    private Integer size = 10;
}
