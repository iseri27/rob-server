package com.xjtu.dbc.robserver.user.login.impl;

import com.xjtu.dbc.robserver.user.login.LoginService;
import com.xjtu.dbc.robserver.user.login.dao.LoginDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginDao loginDao;

    /**
     * 根据用户 ID 与密码验证用户身份
     *
     * @param userId  用户 ID
     * @param userPwd 用户密码
     * @return 验证通过返回 1, 验证失败返回 0
     */
    @Override
    public Integer verifyById(Integer userId, String userPwd) {
        return loginDao.getUserCountByIdAndPwd(userId, userPwd);
    }

    /**
     * 根据用户的邮箱获取 ID
     *
     * @param userEmail 用户邮箱
     * @return 用户 ID
     */
    @Override
    public Integer getUserIdByEmail(String userEmail) {
        return loginDao.getUserIdByEmail(userEmail);
    }

    /**
     * 根据用户名获取 ID
     *
     * @param userName 用户名
     * @return 用户 ID
     */
    @Override
    public Integer getUserIdByName(String userName) {
        return loginDao.getUserIdByName(userName);
    }
}
