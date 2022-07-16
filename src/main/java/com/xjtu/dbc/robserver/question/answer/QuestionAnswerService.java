package com.xjtu.dbc.robserver.question.answer;

import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;

import java.util.List;
import java.util.Map;

public interface QuestionAnswerService {

    /**
     * 根据问题id，查询回答列表
     * @param questionid 回答 ID，uerid 当前用户id，pageParam 分页参数
     * @return 回答详情
     */
    Map<String, Object> getAnswerList(PageParam pageParam, Integer questionid,Integer userid);

    /**
     * 根据问题id，查询优质回答列表
     * @param questionid 回答 ID，uerid 当前用户id，pageParam 分页参数
     * @return 回答详情
     */
    Map<String, Object> getGoodAnswerList(PageParam pageParam,Integer questionid,Integer userid);

    /**
     * 根据回答id，查询回答详情
     * @param answerid 回答 ID
     * @return 回答详情
     */
    AnswerDetailsDto getAnswerDetails(Integer answerid);

    /**
     * 创建回答
     * @param answerDto 回答
     * @return 全部悬赏列表
     */
    void createAnswer(AnswerDto answerDto);

    /**
     * 根据用户的编号与动态的编号来获取该动态的点赞点踩类型
     * @param userid,aticleid  用户编编号与动态的编号
     * @return 该动态的点赞点踩类型
     */
    int getVoteTypeByU_A_id(Integer userid,Integer aticleid);

    /**
     * 当前用户与，uerid之间的关系（是否关注）
     * @param myid 当前用户 ID ，userid 其他用户id
     * @return 两者间的关系
     */
    Integer getRelationship(Integer myid, Integer userid);
}
