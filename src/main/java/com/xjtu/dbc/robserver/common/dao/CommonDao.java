package com.xjtu.dbc.robserver.common.dao;

import com.xjtu.dbc.robserver.common.model.User;

public interface CommonDao {

    // 根据 ID 获取用户数据
    User getUserById(Integer userid);

    // 根据 Id 获取用户的数据（不包括密码）
    User getUserWithoutPwdById(Integer userid);

    // 根据用户名获取其头像链接
    String getuserAvatarByName(String username);

    // 根据用户 ID 获取用户名
    String getUserNameById(Integer userid);

    // 查询用户名数量
    Integer getUserCountByName(String username);
}
