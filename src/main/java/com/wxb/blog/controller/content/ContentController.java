package com.wxb.blog.controller.content;

import com.wxb.blog.biz.content.ContentBiz;
import com.wxb.blog.common.filter.Auth;
import com.wxb.blog.common.utils.ResultUtil;
import com.wxb.blog.model.ResultVO;
import com.wxb.blog.model.form.content.ContentListForm;
import com.wxb.blog.model.form.content.PubContentForm;
import com.wxb.blog.model.vo.content.ContentListVO;
import com.wxb.blog.model.vo.content.PubContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(allowCredentials = "true",origins = "http://recode.wuxiaob.com", maxAge = 3600)
@Controller
@RequestMapping(value = "/content")
public class ContentController {
    @Autowired
    private ContentBiz contentBiz;

    @RequestMapping(value = "/list")
    @ResponseBody
    @Auth
    public ResultVO<ContentListVO> list(@RequestBody ContentListForm form) throws Exception{
        ContentListVO resultVO = contentBiz.list(form);
        return ResultUtil.createSuccessRes(resultVO);
    }


    @RequestMapping(value = "/pub")
    @ResponseBody
    @Auth
    public ResultVO<PubContentVO> pubContent(@RequestBody PubContentForm form) throws Exception{
        PubContentVO resultVO = contentBiz.pubContent(form);
        return ResultUtil.createSuccessRes(resultVO);
    }


}
