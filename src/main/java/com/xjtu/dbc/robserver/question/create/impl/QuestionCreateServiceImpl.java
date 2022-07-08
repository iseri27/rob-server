package com.xjtu.dbc.robserver.question.create.impl;

import com.xjtu.dbc.robserver.question.create.QuestionCreateService;
import com.xjtu.dbc.robserver.question.create.dao.QuestionCreateDao;
import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagListDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


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

    @Override
    public int selectTagNum(int userid) {
        return questionCreateDao.selectTagNum(userid);
    }

    @Override
    public void connectTag(QuestionTagDto questionTagDto) {
        questionCreateDao.connectTag(questionTagDto);
    }

    @Override
    public void disconnectTag(QuestionTagDto questionTagDto) {
        questionCreateDao.disconnectTag(questionTagDto);
    }

    @Override
    public int getQuestionTagNum(int questionid) {
        return questionCreateDao.getQuestionTagNum(questionid);
    }

    @Override
    public List<QuestionTagListDto> getQuestionTagListById(int questionid) {
        return questionCreateDao.getQuestionTagListById(questionid);
    }

}
