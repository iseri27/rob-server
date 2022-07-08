package com.xjtu.dbc.robserver.question.create.dao;

import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagListDto;

import java.util.List;

public interface QuestionCreateDao {


    void createQuestion(QuestionCreateDto dto);

    QuestionCreateDto modifyQuestion(QuestionCreateDto dto);

    QuestionCreateDto findQuestionById(int questionId);

    void deleteQuestionById(int questionid);

    int selectTagNum(int userid);

    void connectTag(QuestionTagDto questionTagDto);

    void disconnectTag(QuestionTagDto questionTagDto);

    int getQuestionTagNum(int questionid);

    List<QuestionTagListDto> getQuestionTagListById(int questionid);
}
