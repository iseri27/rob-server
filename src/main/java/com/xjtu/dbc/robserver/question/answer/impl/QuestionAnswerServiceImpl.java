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

    @Override
    public AnswerDetailsDto getAnswerDetails(Integer answerid) {
        return questionAnswerDao.getAnswerDetails(answerid);
    }

    @Override
    public void createAnswer(AnswerDto answerDto) {
        questionAnswerDao.createAnswer(answerDto);
    }

    @Override
    public int getVoteTypeByU_A_id(Integer userid, Integer articleid) {
        if(questionAnswerDao.getVoteTypeByU_A_id(userid,articleid) != null){
            return questionAnswerDao.getVoteTypeByU_A_id(userid,articleid);
        }
        else{
            return 0;
        }
    }

    @Override
    public Integer getRelationship(Integer myid, Integer userid) {
        return questionAnswerDao.getRelationship(myid, userid);
    }
}
