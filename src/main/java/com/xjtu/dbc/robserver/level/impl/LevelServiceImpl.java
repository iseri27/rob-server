package com.xjtu.dbc.robserver.level.impl;

import com.xjtu.dbc.robserver.level.LevelService;
import com.xjtu.dbc.robserver.level.dao.LevelDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LevelServiceImpl implements LevelService {

    @Resource
    LevelDao levelDao;

    @Override
    public int getExp(int userID) {
        return levelDao.getExp(userID);
    }

    @Override
    public void addExp(int userID, int num) {
        levelDao.updateExp(userID, num);
    }

    @Override
    public int getCans(int userID) {
        return levelDao.getCans(userID);
    }

    @Override
    public void updateCans(int userID, int num) {
        levelDao.updateCans(userID, num);
    }

    @Override
    public int getLevel(int userID) {
        int exp = getExp(userID);
        int value;
        if (exp <= 30) {
            value = 1;
        } else if (exp <= 80) {
            value = 2;
        } else if (exp <= 150) {
            value = 3;
        } else if (exp <= 300) {
            value = 4;
        } else if (exp <= 666) {
            value = 5;
        } else {
            value = 6;
        }
        return value;
    }

}
