package com.xjtu.dbc.robserver.common.impl;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.dao.CommonDao;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class CommonServiceImpl implements CommonService {
    @Resource
    private CommonDao commonDao;

    /**
     * 根据用户 ID 获取 User
     * @param userId 用户 ID
     * @return 用户信息
     */
    @Override
    public User getUserById(Integer userId) {
        return commonDao.getUserById(userId);
    }

    /**
     * 根据用户 ID 获取用户信息，除了密码
     *
     * @param userId 用户 ID
     * @return 用户信息，不包含密码
     */
    @Override
    public User getUserWithoutPasswordById(Integer userId) {
        User user = commonDao.getUserById(userId);
        user.setUserpwd(null);
        return user;
    }

    /**
     * 添加历史记录
     * @param userId      用户 ID
     * @param historyType 历史类型 (在 Constants 中有定义)
     * @param articleId   文本 ID (可以为 null)
     */
    @Override
    public void addHistory(Integer userId, Integer historyType, Integer articleId) {
        commonDao.addHistory(userId, historyType, articleId);
    }

    /**
     * 验证 token 是否有效
     * @param token 令牌
     * @return token 有效返回 true; token 无效返回 false.
     */
    @Override
    public boolean isLogin(String token) {
        try {
            TokenUtils.verifyToken(token, this);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否登录
     * @param tag (tagid tag名称 tag所属)
     */
    @Override
    public void addTag(Tag tag) {
        commonDao.addTag(tag);
    }

    @Override
    public void deleteTag(Integer tagId) {
        commonDao.deleteTag(tagId);
    }
}
