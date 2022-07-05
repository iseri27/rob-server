package com.xjtu.dbc.robserver.common.impl;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.dao.CommonDao;
import com.xjtu.dbc.robserver.common.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service @Transactional
public class CommonServiceImpl implements CommonService {
    @Resource
    private CommonDao commonDao;

    @Override
    public User getUserById(Integer u_id) {
        return commonDao.getUserById(u_id);
    }

    @Override
    public User getUserWithoutPwdById(Integer u_id) {
        return commonDao.getUserWithoutPwdById(u_id);
    }

    @Override
    public String getUserAvatarByName(String u_name) {
        return commonDao.getuserAvatarByName(u_name);
    }

    @Override
    public String selectUserNameById(Integer u_id) {
        return commonDao.getUserNameById(u_id);
    }

    @Override
    public String uploadImg(MultipartFile multipartFile, String token) throws Exception {
        return null;
    }

    @Override
    public Integer selectUserCountByName(String u_name) {
        return commonDao.getUserCountByName(u_name);
    }
}
