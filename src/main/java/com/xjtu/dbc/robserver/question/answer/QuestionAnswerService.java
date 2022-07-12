package com.xjtu.dbc.robserver.question.answer;

import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;

import java.util.List;

public interface QuestionAnswerService {

    /**
     *根据悬赏id获取,其回答列表
     */
    List<QuestionAnswerListDto> getAnswerList(Integer questionid);

    /**
     *根据悬赏id获取,其优质回答列表
     */
    List<QuestionAnswerListDto> getGoodAnswerList(Integer questionid);

    /**
     *根据回答id，查询回答详情
     */
    AnswerDetailsDto getAnswerDetails(Integer answerid);


    void createAnswer(AnswerDto answerDto);
}
