package com.xjtu.dbc.robserver.question.home;

import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;

import java.util.List;
import java.util.Map;

public interface QuestionHomeService {



    /**
     * 根据问题ID查询其被踩数
     * @param questionid 问题编号
     * @return 问题被踩次数
     */
    Integer getDislikenumByQuestionId(Integer questionid);

    /**
     * 根据问题ID查询其点赞数
     * @param questionid 问题编号
     * @return 问题点赞数
     */
    Integer getLikenumByQuestionId(Integer questionid);

    /**
     * 根据问题ID查询其评论数
     * @param questionid 问题编号
     * @return 评论数
     */
    Integer getCommentNum(Integer questionid);

    /**
     * 获取悬赏列表(全部，未解决，已解决)
     * @param
     * @return 列表
     */
    List<QuestionHomeListDto> getAllQuestionList();
    List<QuestionHomeListDto> getNotSolveQuestionList();
    List<QuestionHomeListDto> getSolveQuestionList();

    /**
     *根据悬赏id获取tag表
     */
    List<String> getTagListByQuestionid(int questionid);
}
