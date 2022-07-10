package com.xjtu.dbc.robserver.question.pay;

public interface QuestionPayService {
    /*
    * 判断该问题是否存在优质回答
    */
    int ifFirst(int questionid);

    /*
     * 设置为优质回答并支付悬赏
     */
    void payQuestion(int answerid);

    /*
     * 设置为优质回答
     */
    void setGoodAnswer(int answerid);


}
