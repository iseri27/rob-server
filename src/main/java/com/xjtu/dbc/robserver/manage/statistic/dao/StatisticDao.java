package com.xjtu.dbc.robserver.manage.statistic.dao;

import com.xjtu.dbc.robserver.manage.statistic.entity.*;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 15:44
 */
public interface StatisticDao {
    List<Integer> getUserTotalCount();
    UserSexAgeReport getUserSexAgeReport();
    ActiveUserReport getActiveUserReport();

    List<Integer> getBlogTotalCount();
    BlogNum getBlogNum();
    List<BlogCategory> getBlogCategory();

    List<Integer> getQuestionAnswerCount();
    List<QuestionCategory> getQuestionCategory();

    List<AnswerCategory> getAnswerCategory();
}
