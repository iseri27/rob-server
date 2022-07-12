package com.xjtu.dbc.robserver.manage.statistic;

import com.xjtu.dbc.robserver.manage.statistic.entity.ActiveUserReport;
import com.xjtu.dbc.robserver.manage.statistic.entity.UserSexAgeReport;

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
}
