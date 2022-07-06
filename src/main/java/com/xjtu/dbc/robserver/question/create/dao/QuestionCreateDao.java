package com.xjtu.dbc.robserver.question.create.dao;

import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;

public interface QuestionCreateDao {


    void createQuestion(QuestionCreateDto dto);

    void modifyQuestion(QuestionCreateDto dto);

    void findQuestionById(int questionId);

    void saveQuestion(QuestionCreateDto dto);
}
