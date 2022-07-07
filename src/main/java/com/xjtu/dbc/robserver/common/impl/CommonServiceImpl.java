package com.xjtu.dbc.robserver.common.impl;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.dao.CommonDao;
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
     * 添加历史记录
     * @param userId      用户 ID
     * @param historyType 历史类型 (在 Constants 中有定义)
     * @param articleId   文本 ID (可以为 null)
     */
    @Override
    public void addHistory(Integer userId, Integer historyType, Integer articleId) {
        commonDao.addHistory(userId, historyType, articleId);
    }
}
