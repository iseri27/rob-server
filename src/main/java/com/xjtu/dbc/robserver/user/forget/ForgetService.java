package com.xjtu.dbc.robserver.user.forget;

public interface ForgetService {
    /**
     * 重设密码
     * @param userEmail 用户邮箱
     * @param userPassword 用户的新密码
     */
    void resetPassword(String userEmail, String userPassword);
}
