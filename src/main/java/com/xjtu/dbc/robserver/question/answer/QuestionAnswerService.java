package com.xjtu.dbc.robserver.question.answer;

import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;

import java.util.List;
import java.util.Map;

public interface QuestionAnswerService {

    /**
     *根据悬赏id获取,其回答列表
     */
    Map<String, Object> getAnswerList(PageParam pageParam, Integer questionid,Integer userid);

    /**
     *根据悬赏id获取,其优质回答列表
     */
    Map<String, Object> getGoodAnswerList(PageParam pageParam,Integer questionid,Integer userid);

    /**
     *根据回答id，查询回答详情
     */
    AnswerDetailsDto getAnswerDetails(Integer answerid);


    void createAnswer(AnswerDto answerDto);

    /**
     * 根据用户的编号与动态的编号来获取该动态的点赞点踩类型
     * @param userid,aticleid  用户编编号与动态的编号
     * @return 该动态的点赞点踩类型
     */
    int getVoteTypeByU_A_id(Integer userid,Integer aticleid);
}
