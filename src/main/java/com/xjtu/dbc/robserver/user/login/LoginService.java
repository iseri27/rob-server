package com.xjtu.dbc.robserver.user.login;

public interface LoginService {
    /**
     * 根据用户 ID 与密码验证用户身份
     * @param userid 用户 ID
     * @param userpwd 用户密码
     * @return 验证通过返回 1, 验证失败返回 0
     */
    Integer verifyById(Integer userid, String userpwd);

    /**
     * 根据用户的邮箱获取 ID
     * @param useremail 用户邮箱
     * @return 用户 ID
     */
    Integer getUserIdByEmail(String useremail);

    /**
     * 根据用户名获取 ID
     * @param username 用户名
     * @return 用户 ID
     */
    Integer getUserIdByName(String username);

    /**
     * 添加经验
     * @param userId 用户 ID
     * @param num 增加的经验值数量
     */
    void addExp(Integer userId, Integer num);

    /**
     * 添加用户易拉罐
     * @param userId 用户 ID
     * @param num 增加的易拉罐的数量
     */
    void addCans(Integer userId, Integer num);

    /**
     * 检查本日是否登录过
     * @return 未登录过返回 true; 登录过返回 false
     */
    boolean isFirstLoginToday(Integer userId);
}
