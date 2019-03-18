package com.wxb.blog.model.form.content;

import com.wxb.blog.model.BaseForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContentListForm extends BaseForm {
    /**
     * 页码
     */
    @NotNull
    private Integer page = 1;

    /**
     * 大小
     */
    @NotNull
    private Integer size = 5;

}
