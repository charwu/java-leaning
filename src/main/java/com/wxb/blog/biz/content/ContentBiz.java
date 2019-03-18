package com.wxb.blog.biz.content;

import com.wxb.blog.common.base.BusinessException;
import com.wxb.blog.common.constants.ErrorConstants;
import com.wxb.blog.dao.bean.content.ContentPO;
import com.wxb.blog.dao.bean.content.ContentPOExample;
import com.wxb.blog.dao.bean.content.ext.ContentPOExt;
import com.wxb.blog.dao.mapper.content.ContentPOMapper;
import com.wxb.blog.dao.mapper.content.ext.ContentPOExtMapper;
import com.wxb.blog.model.ResultPageVO;
import com.wxb.blog.model.form.content.ContentListForm;
import com.wxb.blog.model.form.content.PubContentForm;
import com.wxb.blog.model.vo.content.ContentListVO;
import com.wxb.blog.model.vo.content.ContentVO;
import com.wxb.blog.model.vo.content.PubContentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
public class ContentBiz {

    private static final Logger logger = LoggerFactory.getLogger(ContentBiz.class);

    @Autowired
    private ContentPOExtMapper contentPOExtMapper;

    @Autowired
    private ContentPOMapper contentPOMapper;

    public ContentListVO list(ContentListForm form) throws Exception{
        ContentListVO contentListVO = new ContentListVO();
        ResultPageVO pageVO = new ResultPageVO();
        ContentPOExample example = new ContentPOExample();
        Integer limit_start = (form.getPage() - 1) * form.getSize();
        String limit = limit_start + "," + form.getSize();
        example.setOrderByClause(" content.id DESC LIMIT "+ limit);
        pageVO.setCurrentPage(form.getPage());
        pageVO.setPageSize(form.getSize());
        ContentPOExample exampleCount = new ContentPOExample();
        Integer count = contentPOExtMapper.countByExample(exampleCount);
        List<ContentPOExt> list =  contentPOExtMapper.selectByExampleList(example);
        List<ContentVO> data = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list) || count > 0) {
            for (ContentPOExt contentPOExt : list) {
                ContentVO contentVO = new ContentVO();
                BeanUtils.copyProperties(contentPOExt,contentVO);
                contentVO.setPubTime(contentPOExt.getCreateTime().toString());
                data.add(contentVO);
            }
            pageVO.setTotal(count);
            pageVO.setTotalPage((int)Math.ceil((double)count/form.getSize()));
        }
        contentListVO.setList(data);
        contentListVO.setPage(pageVO);
        return contentListVO;
    }

    public PubContentVO pubContent (PubContentForm form)throws Exception{
        PubContentVO pubContentVO = new PubContentVO();
        ContentPO contentPO = new ContentPO();
        contentPO.setContent(form.getContent());
        contentPO.setUid(form.getSessionInfo().getUserId());
        contentPO.setTitle(form.getTitle());
        Integer isDoen = contentPOMapper.insertSelective(contentPO);
        if(isDoen == 0){
            throw new BusinessException(ErrorConstants.PARAMS_ERROR,"发表失败");
        }
        return pubContentVO;
    }

    private Boolean checkContent(String title){
        Boolean isIn = false;
        ContentPOExample example = new ContentPOExample();
        ContentPOExample.Criteria criteria = example.createCriteria();
        criteria.andTitleEqualTo(title);
        List<ContentPO> list = contentPOMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            isIn = true;
        }
        return isIn;
    }

    private  String dateToStamp(String s) throws Exception{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}
