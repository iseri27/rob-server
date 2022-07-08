package com.xjtu.dbc.robserver.common;


import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.model.user.User;

public interface CommonService {
    /**
     * 根据用户 ID 获取 User
     * @param userId 用户 ID
     * @return 用户信息
     */
    User getUserById(Integer userId);

    /**
     * 根据用户 ID 获取用户信息，除了密码
     * @param userId 用户 ID
     * @return 用户信息，不包含密码
     */
    User getUserWithoutPasswordById(Integer userId);

    /**
     * 添加历史记录
     * @param userId 用户 ID
     * @param historyType 历史类型 (在 Constants 中有定义)
     * @param articleId 文本 ID (可以为 null)
     */
    void addHistory(Integer userId, Integer historyType, Integer articleId);

    /**
     * 验证是否登录
     * @param token 令牌
     * @return 登录有效返回 true; 未登录或登录无效返回 false.
     */
    boolean isLogin(String token);

    /**
     * 用户新增tag
     * @param tag (tagid tag名称 tag所属)
     */
    void addTag(Tag tag);

    /**
     * 删除用户的tag
     * @param tagid
     */
    void deleteTag(int tagid);
}
