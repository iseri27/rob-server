package com.xjtu.dbc.robserver.question.answer.dao;

import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;


import java.util.List;

public interface QuestionAnswerDao {

    /**
     * 获取对应悬赏的全部回答列表
     * @param questionid 悬赏 ID
     * @return 全部悬赏列表
     */
    List<QuestionAnswerListDto> getAllAnswerList(Integer questionid);

    /**
     * 获取回答详情
     * @param answerid 回答 ID
     * @return 回答详情
     */
    AnswerDetailsDto getAnswerDetails(Integer answerid);

    /**
     * 创建回答
     * @param answerDto 回答
     */
    void createAnswer(AnswerDto answerDto);

    /**
     * 获取优质回答列表
     * @param questionid 问题 ID
     * @return 优质回答列表
     */
    List<QuestionAnswerListDto> getGoodAnswerList(Integer questionid);

    /**
     * 获取当前用户对应当前文章的点赞类型
     * @param userid 当前用户 ID，articleid 文章id
     * @return 点赞类型
     */
    Integer getVoteTypeByU_A_id(Integer userid, Integer articleid);

    /**
     * 当前用户与，uerid之间的关系（是否关注）
     * @param myid 当前用户 ID ，userid 其他用户id
     * @return 两者间的关系
     */
    Integer getRelationship(Integer myid, Integer userid);
}
