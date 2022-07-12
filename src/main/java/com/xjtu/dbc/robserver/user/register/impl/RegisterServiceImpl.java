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
     * @param userEmail 用户邮箱
     * @return 持有该邮箱的用户数量
     */
    @Override
    public Integer getUserCountByEmail(String userEmail) {
        return registerDao.getUserCountByEmail(userEmail);
    }

    /**
     * 用户注册
     * @param registerDto {用户名, 用户邮箱, 用户生日, 用户性别}
     */
    @Override
    public void addUser(RegisterDto registerDto) {
        registerDao.addUser(registerDto);
    }

    /**
     * 为用户创建关注列表
     *
     * @param userId       用户 ID
     * @param userListType 用户列表的类型
     */
    @Override
    public void createUserList(Integer userId, Integer userListType) {
        registerDao.addUserList(userId, userListType);
    }

    /**
     * 为用户创建收藏夹
     * @param userId 用户 ID
     */
    @Override
    public void createBookmark(Integer userId) {
        registerDao.addBookmark(userId);
    }
}
