package com.wxb.blog.controller.math;

import com.wxb.blog.biz.math.MathBiz;
import com.wxb.blog.common.utils.ResultUtil;
import com.wxb.blog.model.ResultVO;
import com.wxb.blog.model.form.math.MathForm;
import com.wxb.blog.model.vo.math.MathVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/math")
public class MathController {
    @Autowired
    private MathBiz mathBiz;

    @RequestMapping(value = "/run")
    @ResponseBody
    public ResultVO<List<MathVO>> run(@RequestBody MathForm form){
        List<MathVO> list = mathBiz.run(form);
        return ResultUtil.createSuccessRes(list);
    }
}
