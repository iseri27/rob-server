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

}
