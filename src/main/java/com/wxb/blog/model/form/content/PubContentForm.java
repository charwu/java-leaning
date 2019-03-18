package com.wxb.blog.model.form.content;

import com.wxb.blog.model.BaseForm;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PubContentForm extends BaseForm {
    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * 内容
     */
    @NotNull
    private String content;

}
