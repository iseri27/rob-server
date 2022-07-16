package com.xjtu.dbc.robserver.user.login.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.xjtu.dbc.robserver.level.dao.LevelDao;
import com.xjtu.dbc.robserver.user.login.LoginService;
import com.xjtu.dbc.robserver.user.login.dao.LoginDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service @Transactional
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginDao loginDao;

    @Resource
    private LevelDao levelDao;

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

    /**
     * 添加经验
     * @param userId 用户 ID
     * @param num 增加的经验值
     */
    @Override
    public void addExp(Integer userId, Integer num) {
        levelDao.updateExp(userId, num);
    }

    /**
     * 添加用户易拉罐
     * @param userId 用户 ID
     * @param num 增加的易拉罐数量
     */
    @Override
    public void addCans(Integer userId, Integer num) {
        levelDao.updateCans(userId, num);
    }

    /**
     * 检查本日是否登录过
     *
     * @return 未登录过返回 true; 登录过返回 false
     */
    @Override
    public boolean isFirstLoginToday(Integer userId) {
        // 获取当前时间
        DateTime currentTime = DateUtil.date();
        // 获取今天的起始时间
        DateTime beginOfToday = DateUtil.beginOfDay(currentTime);
        // 搜索历史
        Integer cnt = loginDao.getLoginHistoryCountOfToday(userId, beginOfToday);

        return cnt == 0;
    }
}
