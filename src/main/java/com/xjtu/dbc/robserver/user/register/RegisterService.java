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
     * @return 用户的 ID
     */
    void addUser(RegisterDto registerDto);

    /**
     * 获取当前最大的用户的 ID
     * @return 最大用户 ID
     */
    Integer getMaxId();
}
