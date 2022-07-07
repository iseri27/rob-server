package com.xjtu.dbc.robserver.question.create.impl;

import com.xjtu.dbc.robserver.question.create.QuestionCreateService;
import com.xjtu.dbc.robserver.question.create.dao.QuestionCreateDao;
import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service @Transactional
public class QuestionCreateServiceImpl implements QuestionCreateService {

    @Resource
    private QuestionCreateDao questionCreateDao;

    @Override
    public void createQuestion(QuestionCreateDto dto){
        questionCreateDao.createQuestion(dto);
    }

    @Override
    public void modifyQuestion(QuestionCreateDto dto) {
        questionCreateDao.modifyQuestion(dto);
    }

    @Override
    public QuestionCreateDto findQuestionById(int questionid) {
        return questionCreateDao.findQuestionById(questionid);
    }

    @Override
    public void saveQuestion(QuestionCreateDto dto) {
        questionCreateDao.createQuestion(dto);
    }

    @Override
    public void deleteQuestionById(int questionid) {
        questionCreateDao.deleteQuestionById(questionid);
    }

}
