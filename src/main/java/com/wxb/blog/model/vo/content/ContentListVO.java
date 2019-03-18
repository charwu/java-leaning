package com.wxb.blog.model.vo.content;

import com.wxb.blog.model.ResultPageVO;
import lombok.Data;

import java.util.List;

@Data
public class ContentListVO {

    /**
     * 数据列表
     */
    private List<ContentVO> list;

    /**
     * page
     */
    private ResultPageVO page;
}
