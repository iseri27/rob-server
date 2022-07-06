package com.xjtu.dbc.robserver.user.register.dao;

import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;

public interface RegisterDao {
    /**
     * 根据用户的昵称获取用户数量
     * @param username 用户名称
     * @return 数据库中有该名称的用户的数量
     */
    Integer getUserCountByName(String username);

    /**
     * 根据用户的邮箱获取用户数量
     * @param useremail 用户邮箱
     * @return 数据库中有该名称的用户的数量
     */
    int getUserCountByEmail(String useremail);

    /**
     * 添加用户
     * @param registerDto 用户的信息
     */
    void addUser(RegisterDto registerDto);

    /**
     * 为用户创建用户列表
     * @param userId 用户 ID
     * @param userListType 用户列表的类型
     */
    void addUserList(Integer userId, Integer userListType);

    /**
     * 为用户创建收藏夹
     * @param userId 用户 ID
     */
    void addBookmark(Integer userId);
}
