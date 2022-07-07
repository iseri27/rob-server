package com.xjtu.dbc.robserver.user.personal.impl;

import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.user.personal.PersonalService;
import com.xjtu.dbc.robserver.user.personal.dao.PersonalDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class PersonalServiceImpl implements PersonalService {
    @Resource
    private PersonalDao personalDao;
    @Override
    public void getMyAvatar(Integer uid, String url) {
        personalDao.getMyAvatar(uid, url);
    }

    @Override
    public void changeInformation(User user) {
        personalDao.changeInformation(user);
    }

    @Override
    public User checkPassword(User user) {
        return personalDao.checkPassword(user);
    }

    @Override
    public void changePassword(User user) {
        personalDao.changePassword(user);
    }
}
