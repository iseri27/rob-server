package com.xjtu.dbc.robserver.user.register;

public interface RegisterService {
    /**
     * 根据用户的昵称获取用户数量
     * @param userName 用户名称
     * @return 数据库中有该名称的用户的数量
     */
    Integer getUserCountByName(String userName);
}
