package com.xjtu.dbc.robserver.manage.statistic;

import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.manage.statistic.entity.*;
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
     * 获取用户总数分析
     *
     * @return [普通用户数，管理员数量，总易拉罐数]
     */
    @GetMapping("/user/total")
    public Result getUserTotalCount() {
        List<Integer> userTotal = statisticService.getUserTotalCount();
        return Result.success("获取用户总数分析成功", userTotal);
    }

    /**
     * 获取用户性别年龄分布
     *
     * @return 用户男女比与五个年龄段分布数量
     */
    @GetMapping("/user/sex&age")
    public Result getUserSexAgeReport() {
        UserSexAgeReport userSexAgeReport = statisticService.getUserSexAgeReport();
        return Result.success("获取用户性别年龄分析成功", userSexAgeReport);
    }

    /**
     * 获取最近十五日活跃用户数量
     *
     * @return [log1, log2, ..., log15]
     */
    @GetMapping("/user/active")
    public Result getActiveUserReport() {
        ActiveUserReport activeUserReport = statisticService.getActiveUserReport();
        return Result.success("获取最近十五日活跃用户成功", activeUserReport);
    }

    /**
     * 获取博客总数分析
     *
     * @return [博客总数，总阅读量，总点赞量]
     */
    @GetMapping("/blog/total")
    public Result getBlogTotalCount() {
        List<Integer> userTotal = statisticService.getBlogTotalCount();
        return Result.success("获取博客总数分析成功", userTotal);
    }

    /**
     * 获取最近十五日博客数
     *
     * @return [log1, log2, ..., log15]
     */
    @GetMapping("/blog/num")
    public Result getBlogNum() {
        BlogNum blogNum = statisticService.getBlogNum();
        return Result.success("获取最近十五日博客数成功", blogNum);
    }

    /**
     * 获取博客分区与数量
     *
     * @return List[分区, 数量]
     */
    @GetMapping("/blog/category")
    public Result getBlogCategory() {
        List<BlogCategory> blogCategories = statisticService.getBlogCategory();
        return Result.success("获取博客种类数成功", blogCategories);
    }

    /**
     * 获取提问回答总数分析
     *
     * @return [提问总数，回答总数，已解决问题数]
     */
    @GetMapping("/qa/total")
    public Result  getQuestionAnswerCount() {
        List<Integer> qaTotal = statisticService.getQuestionAnswerCount();
        return Result.success("获取博客总数分析成功", qaTotal);
    }

    /**
     * 获取问题分区与数量
     *
     * @return List[分区, 数量]
     */
    @GetMapping("/qa/qcategory")
    public Result getQuestionCategory() {
        List<QuestionCategory> questionCategories = statisticService.getQuestionCategory();
        return Result.success("获取问题分区数成功", questionCategories);
    }

    /**
     * 获取回答分区与数量
     *
     * @return List[分区, 数量]
     */
    @GetMapping("/qa/acategory")
    public Result getAnswerCategory() {
       List<AnswerCategory> answerCategories = statisticService.getAnswerCategory();
        return Result.success("获取回答分区数成功", answerCategories);
    }
}
