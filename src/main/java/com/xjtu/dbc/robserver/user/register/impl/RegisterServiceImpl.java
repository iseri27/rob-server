package com.xjtu.dbc.robserver.user.register.impl;

import com.xjtu.dbc.robserver.user.register.RegisterService;
import com.xjtu.dbc.robserver.user.register.dao.RegisterDao;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;
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

    /**
     * 根据用户邮箱获取用户数量
     *
     * @param useremail 用户邮箱
     * @return 持有该邮箱的用户数量
     */
    @Override
    public int getUserCountByEmail(String useremail) {
        return registerDao.getUserCountByEmail(useremail);
    }

    /**
     * 用户注册
     *
     * @param registerDto {用户名, 用户邮箱, 用户生日, 用户性别}
     */
    @Override
    public void addUser(RegisterDto registerDto) {
        registerDao.addUser(registerDto);
    }

    /**
     * 获取当前最大的用户的 ID
     * @return 最大用户 ID
     */
    @Override
    public Integer getMaxId() {
        return registerDao.getMaxId();
    }
}
