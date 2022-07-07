package com.xjtu.dbc.robserver.question.create.dao;

import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;

public interface QuestionCreateDao {


    void createQuestion(QuestionCreateDto dto);

    QuestionCreateDto modifyQuestion(QuestionCreateDto dto);

    QuestionCreateDto findQuestionById(int questionId);

    void deleteQuestionById(int questionid);
}
