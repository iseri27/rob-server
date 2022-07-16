package com.xjtu.dbc.robserver.user.login.dao;

import cn.hutool.core.date.DateTime;

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
     * 获取今日的登录历史数量
     * @param beginOfToday 今日开始时间
     * @return 数量
     */
    Integer getLoginHistoryCountOfToday(Integer userId, DateTime beginOfToday);

}
