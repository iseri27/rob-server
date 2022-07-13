package com.xjtu.dbc.robserver.manage.statistic;

import com.xjtu.dbc.robserver.manage.statistic.entity.*;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 15:43
 */
public interface StatisticService {
    List<Integer> getUserTotalCount();
    UserSexAgeReport getUserSexAgeReport();
    ActiveUserReport getActiveUserReport();

    List<Integer> getBlogTotalCount();
    BlogNum getBlogNum();
    List<BlogCategory> getBlogCategory();

    List<Integer> getQuestionAnswerCount();
    List<QuestionCategory> getQuestionCategory();
}
