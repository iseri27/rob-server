package com.xjtu.dbc.robserver.user.register;

import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;

public interface RegisterService {
    /**
     * 根据用户的昵称获取用户数量
     * @param userName 用户名称
     * @return 数据库中有该名称的用户的数量
     */
    Integer getUserCountByName(String userName);

    /**
     * 根据用户邮箱获取用户数量
     * @param useremail  用户邮箱
     * @return 持有该邮箱的用户数量
     */
    int getUserCountByEmail(String useremail);

    /**
     * 用户注册
     * @param registerDto {用户名, 用户邮箱, 用户生日, 用户性别}
     */
    void addUser(RegisterDto registerDto);

    /**
     * 为用户创建关注列表
     * @param userId 用户 ID
     * @param userListType 用户列表的类型
     */
    void createUserList(Integer userId, Integer userListType);

    /**
     * 为用户创建收藏夹
     * @param userId 用户 ID
     */
    void createBookmark(Integer userId);
}
