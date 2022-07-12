package com.xjtu.dbc.robserver.question.answer.impl;

import com.xjtu.dbc.robserver.blog.reply.BlogReplyService;
import com.xjtu.dbc.robserver.question.answer.QuestionAnswerService;
import com.xjtu.dbc.robserver.question.answer.dao.QuestionAnswerDao;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service @Transactional
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    @Resource
    private QuestionAnswerDao questionAnswerDao;


    @Override
    public List<QuestionAnswerListDto> getAnswerList(Integer questionid) {
        return questionAnswerDao.getAllAnswerList(questionid);
    }

    @Override
    public List<QuestionAnswerListDto> getGoodAnswerList(Integer questionid) {
        return questionAnswerDao.getGoodAnswerList(questionid);
    }

    @Override
    public AnswerDetailsDto getAnswerDetails(Integer answerid) {
        return questionAnswerDao.getAnswerDetails(answerid);
    }

    @Override
    public void createAnswer(AnswerDto answerDto) {
        questionAnswerDao.createAnswer(answerDto);
    }
}
