package com.xjtu.dbc.robserver.question.home.dao;

import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.question.home.entity.QuestionDetailsDto;
import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;

import java.util.List;

public interface QuestionHomeDao {

    /**
     *获取点踩数
     */
    Integer getDislikenumByQuestionId(Integer questionid);

    /**
     *获取点赞数
     */
    Integer getLikenumByQuestionId(Integer questionid);

    /**
     *获取评论数
     */
    Integer getCommentNum(Integer questionid);

    /**
     *获取全部列表
     */
    List<QuestionHomeListDto> getAllQuestionList(int categoryid);

    /**
     *获取未解决悬赏列表
     */
    List<QuestionHomeListDto> getNotSolveQuestionList(int categoryid);

    /**
     *获取已解决悬赏列表
     */
    List<QuestionHomeListDto> getSolveQuestionList(int categoryid);

    /**
     *获取悬赏对应的标签名称
     */
    List<String> getTagListByQuestionid(int questionid);

    /**
     *获取悬赏的详细信息
     */
    QuestionDetailsDto getQuestionDetails(int questionid);

    /**
     *获取分区列表
     */
    List<Category> getCategory();

    /**
     *获取悬赏的回答数
     */
    Integer countAnswer(int questionid);

    /**
     *获取点赞类型
     */
    Integer getVoteTypeByU_A_id(int userid, Integer questionid);
}
