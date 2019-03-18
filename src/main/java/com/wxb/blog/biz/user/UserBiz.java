package com.wxb.blog.biz.user;

import com.wxb.blog.common.utils.MD5Util;
import com.wxb.blog.common.utils.StringUtils;
import com.wxb.blog.dao.bean.user.UserPO;
import com.wxb.blog.dao.bean.user.UserPOExample;
import com.wxb.blog.dao.mapper.user.UserPOMapper;
import com.wxb.blog.model.form.user.UserInfoForm;
import com.wxb.blog.model.vo.user.UserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserBiz {
    private static final Logger logger = LoggerFactory.getLogger(UserBiz.class);

    @Autowired
    private UserPOMapper userPOMapper;

    /**
     * 根据账号密码判断是否正常
     * @param po
     * @return
     */
    public UserPO getUserInfo(UserPO po){
        UserPOExample example = new UserPOExample();
        UserPOExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(po.getUsername());
        criteria.andPasswordEqualTo(MD5Util.getMD5String(po.getPassword()));
        List<UserPO> list = userPOMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 保存用户信息
     * @param po
     * @return
     */
    public Boolean saveUserInfo(UserPO po){
        int insert = userPOMapper.insertSelective(po);
        if(insert > 0){
            return true;
        }
        return false;
    }

    /**
     * 检查用户是否注册
     * @param userName
     * @return
     */
    public Boolean checkUserInfo(String userName){
        UserPOExample example = new UserPOExample();
        UserPOExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<UserPO> list = userPOMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    public UserInfoVO getUserInfoByUserId(Integer userId){
        UserInfoVO userInfoVO = new UserInfoVO();
        UserPO info = userPOMapper.selectByPrimaryKey(userId);
        if(info != null){
            BeanUtils.copyProperties(info,userInfoVO);
        }else {
            return null;
        }
        return userInfoVO;
    }


    public Boolean updateUserInfo(UserInfoForm form){
        UserPOExample example = new UserPOExample();
        UserPOExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(form.getSessionInfo().getUserId());
        UserPO userPO = new UserPO();
        if(!StringUtils.isBlank(form.getNickName())){
            userPO.setNickName(form.getNickName());
        }
        if(!StringUtils.isBlank(form.getBirthday())){
            userPO.setBirthday(form.getBirthday());
        }
        if(!StringUtils.isBlank(form.getHeadUrl())){
            userPO.setHeadUrl(form.getHeadUrl());
        }
        if(form.getSex().intValue() != 0){
            userPO.setSex(form.getSex());
        }
        if(!StringUtils.isBlank(form.getSummary())){
            userPO.setSummary(form.getSummary());
        }
        int update = userPOMapper.updateByExampleSelective(userPO,example);
        if(update > 0){
            return true;
        }
        return false;
    }
}
