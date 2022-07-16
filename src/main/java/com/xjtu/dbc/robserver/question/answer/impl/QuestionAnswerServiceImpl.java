package com.xjtu.dbc.robserver.question.answer.impl;


import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.common.page.QueryAction;
import com.xjtu.dbc.robserver.question.answer.QuestionAnswerService;
import com.xjtu.dbc.robserver.question.answer.dao.QuestionAnswerDao;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service @Transactional
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Resource
    private QuestionAnswerDao questionAnswerDao;

    /**
     * 根据问题id，查询回答列表
     * @param questionid 回答 ID，uerid 当前用户id，pageParam 分页参数
     * @return 回答详情
     */
    @Override
    public Map<String, Object> getAnswerList(PageParam pageParam, Integer questionid,Integer userid) {
        class queryAction implements QueryAction<QuestionAnswerListDto> {
            @Override
            public List<QuestionAnswerListDto> execute() {
                List<QuestionAnswerListDto> questionAnswerList = questionAnswerDao.getAllAnswerList(questionid);
                for (QuestionAnswerListDto questionAnswerListDto: questionAnswerList) {
                    questionAnswerListDto.setVote_type(questionAnswerDao.getVoteTypeByU_A_id(userid,questionAnswerListDto.getAnswerid()));
                }
                return questionAnswerList;
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    /**
     * 根据问题id，查询优质回答列表
     * @param questionid 回答 ID，uerid 当前用户id，pageParam 分页参数
     * @return 回答详情
     */
    @Override
    public Map<String, Object> getGoodAnswerList(PageParam pageParam, Integer questionid,Integer userid) {
        class queryAction implements QueryAction<QuestionAnswerListDto> {
            @Override
            public List<QuestionAnswerListDto> execute() {
                List<QuestionAnswerListDto> questionAnswerList = questionAnswerDao.getGoodAnswerList(questionid);
                for (QuestionAnswerListDto questionAnswerListDto: questionAnswerList) {
                    questionAnswerListDto.setVote_type(questionAnswerDao.getVoteTypeByU_A_id(userid,questionAnswerListDto.getAnswerid()));
                }
                return questionAnswerList;
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    /**
     * 根据回答id，查询回答详情
     * @param answerid 回答 ID
     * @return 回答详情
     */
    @Override
    public AnswerDetailsDto getAnswerDetails(Integer answerid) {
        return questionAnswerDao.getAnswerDetails(answerid);
    }

    /**
     * 创建回答
     * @param answerDto 回答
     * @return 全部悬赏列表
     */
    @Override
    public void createAnswer(AnswerDto answerDto) {
        questionAnswerDao.createAnswer(answerDto);
    }

    /**
     * 根据用户的编号与动态的编号来获取该动态的点赞点踩类型
     * @param userid,aticleid  用户编编号与动态的编号
     * @return 该动态的点赞点踩类型
     */
    @Override
    public int getVoteTypeByU_A_id(Integer userid, Integer articleid) {
        if(questionAnswerDao.getVoteTypeByU_A_id(userid,articleid) != null){
            return questionAnswerDao.getVoteTypeByU_A_id(userid,articleid);
        }
        else{
            return 0;
        }
    }

    /**
     * 当前用户与，uerid之间的关系（是否关注）
     * @param myid 当前用户 ID ，userid 其他用户id
     * @return 两者间的关系
     */
    @Override
    public Integer getRelationship(Integer myid, Integer userid) {
        return questionAnswerDao.getRelationship(myid, userid);
    }
}
