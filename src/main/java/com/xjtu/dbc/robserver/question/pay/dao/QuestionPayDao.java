package com.xjtu.dbc.robserver.question.pay.dao;

public interface QuestionPayDao {
    int countGoodAnswer(int questionid);

    void payQuestion();

    void setGoodAnswer();
}
