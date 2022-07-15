package com.xjtu.dbc.robserver.manage.statistic.impl;

import com.xjtu.dbc.robserver.manage.statistic.StatisticService;
import com.xjtu.dbc.robserver.manage.statistic.dao.StatisticDao;
import com.xjtu.dbc.robserver.manage.statistic.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 15:45
 */
@Transactional
@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    StatisticDao statisticDao;
    @Override
    public List<Integer> getUserTotalCount() {
        return statisticDao.getUserTotalCount();
    }

    @Override
    public UserSexAgeReport getUserSexAgeReport() {
        return statisticDao.getUserSexAgeReport();
    }

    @Override
    public ActiveUserReport getActiveUserReport() {
        return statisticDao.getActiveUserReport();
    }

    @Override
    public List<Integer> getBlogTotalCount() {
        return statisticDao.getBlogTotalCount();
    }

    @Override
    public BlogNum getBlogNum() {
        return statisticDao.getBlogNum();
    }

    @Override
    public List<BlogCategory> getBlogCategory() {
        return statisticDao.getBlogCategory();
    }

    @Override
    public List<Integer> getQuestionAnswerCount() {
        return statisticDao.getQuestionAnswerCount();
    }

    @Override
    public List<QuestionCategory> getQuestionCategory() {
        return statisticDao.getQuestionCategory();
    }

    @Override
    public List<AnswerCategory> getAnswerCategory() {
        return statisticDao.getAnswerCategory();
    }
}
