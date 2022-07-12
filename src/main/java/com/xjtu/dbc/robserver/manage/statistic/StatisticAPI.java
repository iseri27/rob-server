package com.xjtu.dbc.robserver.manage.statistic;

import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.manage.statistic.entity.UserSexAgeReport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 15:43
 */

@RestController
@RequestMapping("/statistic")
public class StatisticAPI {
    @Resource
    private StatisticService statisticService;

    /**
     * 获取总数分析
     * @return [普通用户数，管理员数量，总浏览量]
     */
    @GetMapping("/user/total")
    public Result getUserTotalCount(){
        List<Integer> userTotal = statisticService.getUserTotalCount();
        return  Result.success("获取总数分析成功",userTotal);
    }

    /**
     * 获取用户性别年龄分布
     * @return 用户男女比与五个年龄段分布数量
     */
    @GetMapping("/user/sex&age")
    public Result getUserSexAgeReport(){
        UserSexAgeReport userSexAgeReport = statisticService.getUserSexAgeReport();
        return Result.success("获取用户性别年龄分析成功",userSexAgeReport);
    }
}
