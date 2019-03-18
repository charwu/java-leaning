package com.wxb.blog.dao.mapper.content;

import com.wxb.blog.dao.bean.content.ContentPO;
import com.wxb.blog.dao.bean.content.ContentPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentPOMapper {
    int countByExample(ContentPOExample example);

    int deleteByExample(ContentPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ContentPO record);

    int insertSelective(ContentPO record);

    List<ContentPO> selectByExample(ContentPOExample example);

    ContentPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ContentPO record, @Param("example") ContentPOExample example);

    int updateByExample(@Param("record") ContentPO record, @Param("example") ContentPOExample example);

    int updateByPrimaryKeySelective(ContentPO record);

    int updateByPrimaryKey(ContentPO record);
}