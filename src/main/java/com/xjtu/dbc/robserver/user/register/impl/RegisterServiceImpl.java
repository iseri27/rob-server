package com.xjtu.dbc.robserver.user.register.impl;

import com.xjtu.dbc.robserver.user.register.RegisterService;
import com.xjtu.dbc.robserver.user.register.dao.RegisterDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private RegisterDao registerDao;

    /**
     * 根据用户的昵称获取用户数量
     * @param userName 用户名称
     * @return 数据库中有该名称的用户的数量
     */
    @Override
    public Integer getUserCountByName(String userName) {
        return registerDao.getUserCountByName(userName);
    }
}
