package com.xjtu.dbc.robserver.user.login.dao;

public interface LoginDao {
    /**
     * 根据用户 ID 与密码获取用户数量
     * @param userid 用户 ID
     * @param userpwd 用户密码
     * @return 用户数量
     */
    Integer getUserCountByIdAndPwd(Integer userid, String userpwd);

    /**
     * 根据用户名获取用户的 ID
     * @param username 用户名
     * @return 用户 ID
     */
    Integer getUserIdByName(String username);

    /**
     * 根据用户邮箱获取用户 ID
     * @param useremail 用户邮箱
     * @return 用户 ID
     */
    Integer getUserIdByEmail(String useremail);

    /**
     * 添加登录历史
     * @param userId 用户 ID
     */
    void addHistory(Integer userId);

}
