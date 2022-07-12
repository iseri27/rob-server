package com.xjtu.dbc.robserver.user.forget.dao;

public interface ForgetDao {
    /**
     * 根据用户邮箱重设密码
     * @param userEmail 用户邮箱
     * @param userPassword 用户密码
     */
    void updateUserPassword(String userEmail, String userPassword);
}
