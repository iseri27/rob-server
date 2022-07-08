package com.xjtu.dbc.robserver.question.answer.impl;

import com.xjtu.dbc.robserver.blog.reply.BlogReplyService;
import com.xjtu.dbc.robserver.question.answer.QuestionAnswerService;
import com.xjtu.dbc.robserver.question.answer.dao.QuestionAnswerDao;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
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
        return questionAnswerDao.getAList(questionid);
    }

    @Override
    public Integer getDislikenumByAnswerId(Integer answerid) {
        return questionAnswerDao.getDislikenumByAnswerId(answerid);
    }

    @Override
    public Integer getLikenumByAnswerId(Integer answerid) {
        return questionAnswerDao.getLikenumByAnswerId(answerid);
    }

    @Override
    public Integer getCommentNum(Integer answerid) {
        return questionAnswerDao.getCommentNum(answerid);
    }

    @Override
    public AnswerDetailsDto getAnswerDetails(Integer answerid) {
        return questionAnswerDao.getAnswerDetails(answerid);
    }
}
