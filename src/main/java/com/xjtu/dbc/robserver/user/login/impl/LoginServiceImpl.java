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
     * @param userid  用户 ID
     * @param userpwd 用户密码
     * @return 验证通过返回 1, 验证失败返回 0
     */
    @Override
    public Integer verifyById(Integer userid, String userpwd) {
        return loginDao.getUserCountByIdAndPwd(userid, userpwd);
    }

    /**
     * 根据用户的邮箱获取 ID
     *
     * @param useremail 用户邮箱
     * @return 用户 ID
     */
    @Override
    public Integer getUserIdByEmail(String useremail) {
        return loginDao.getUserIdByEmail(useremail);
    }

    /**
     * 根据用户名获取 ID
     *
     * @param username 用户名
     * @return 用户 ID
     */
    @Override
    public Integer getUserIdByName(String username) {
        return loginDao.getUserIdByName(username);
    }
}
