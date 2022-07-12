package com.xjtu.dbc.robserver.user.forget.impl;

import com.xjtu.dbc.robserver.user.forget.ForgetService;
import com.xjtu.dbc.robserver.user.forget.dao.ForgetDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class ForgetServiceImpl implements ForgetService {
    @Resource
    private ForgetDao forgetDao;

    /**
     * 重设密码
     * @param userEmail    用户邮箱
     * @param userPassword 用户的新密码
     */
    @Override
    public void resetPassword(String userEmail, String userPassword) {
        forgetDao.updateUserPassword(userEmail, userPassword);
    }
}
