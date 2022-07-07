package com.xjtu.dbc.robserver.common;


import com.xjtu.dbc.robserver.common.model.user.User;

public interface CommonService {
    /**
     * 根据用户 ID 获取 User
     * @param userId 用户 ID
     * @return 用户信息
     */
    User getUserById(Integer userId);

    /**
     * 添加历史记录
     * @param userId 用户 ID
     * @param historyType 历史类型 (在 Constants 中有定义)
     * @param articleId 文本 ID (可以为 null)
     */
    void addHistory(Integer userId, Integer historyType, Integer articleId);
}
