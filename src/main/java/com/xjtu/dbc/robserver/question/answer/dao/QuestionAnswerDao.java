package com.xjtu.dbc.robserver.question.answer.dao;

import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;


import java.util.List;

public interface QuestionAnswerDao {
    List<QuestionAnswerListDto> getAllAnswerList(Integer questionid);

    Integer getDislikenumByAnswerId(Integer answerid);

    Integer getLikenumByAnswerId(Integer answerid);

    Integer getCommentNum(Integer answerid);

    AnswerDetailsDto getAnswerDetails(Integer answerid);

    void createAnswer(AnswerDto answerDto);

    List<QuestionAnswerListDto> getGoodAnswerList(Integer questionid);

    Integer getVoteTypeByU_A_id(Integer userid, Integer articleid);
}
