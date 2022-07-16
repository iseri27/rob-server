package com.xjtu.dbc.robserver.question.pay.dao;

public interface QuestionPayDao {

    /*
     * 判断该问题是否存在优质回答
     */
    int countGoodAnswer(int questionid);

    /*
     * 设置为优质回答并支付悬赏
     */
    void payQuestion(int questionid);

    /*
     * 设置为优质回答
     */
    void setGoodAnswer(int answerid);
}
