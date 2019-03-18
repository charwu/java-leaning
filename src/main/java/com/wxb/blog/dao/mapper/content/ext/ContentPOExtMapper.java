package com.wxb.blog.dao.mapper.content.ext;

import com.wxb.blog.dao.bean.content.ContentPO;
import com.wxb.blog.dao.bean.content.ContentPOExample;
import com.wxb.blog.dao.bean.content.ext.ContentPOExt;
import com.wxb.blog.dao.bean.content.ext.ContentPOExtExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContentPOExtMapper {

    int countByExample(ContentPOExample example);

    List<ContentPOExt> selectByExampleList(ContentPOExample example);

    List<ContentPOExt> selectByExample(@Param("req")ContentPOExtExample example);

    ContentPOExt selectByPrimaryKey(Integer id);
}