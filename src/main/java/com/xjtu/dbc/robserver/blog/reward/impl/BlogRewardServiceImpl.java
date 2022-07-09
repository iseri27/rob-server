package com.xjtu.dbc.robserver.blog.reward.impl;

import com.xjtu.dbc.robserver.blog.reward.BlogRewardService;
import com.xjtu.dbc.robserver.blog.reward.RewardDto;
import com.xjtu.dbc.robserver.blog.reward.dao.BlogRewardDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BlogRewardServiceImpl implements BlogRewardService {
    @Resource
    private BlogRewardDao blogRewardDao;

    @Override
    public void insertHistory(RewardDto dto){
        blogRewardDao.insertHistory(dto);
    }

    @Override
    public int getAuthoridByArticleid(Integer articleid){
        return blogRewardDao.getAuthoridByArticleid(articleid);
    }

    @Override
    public int getRewardHistory(RewardDto dto){
        return blogRewardDao.getRewrdHistory(dto);
    }
}
