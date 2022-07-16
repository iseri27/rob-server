package com.xjtu.dbc.robserver.question.pay.impl;


import com.xjtu.dbc.robserver.question.pay.QuestionPayService;
import com.xjtu.dbc.robserver.question.pay.dao.QuestionPayDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class QuestionPayServiceImpl implements QuestionPayService {

    @Resource
    private QuestionPayDao questionPayDao;

    /*
     * 判断该问题是否存在优质回答
     */
    @Override
    public int ifFirst(int questionid) {
        return questionPayDao.countGoodAnswer(questionid);
    }

    /*
     * 设置为优质回答并支付悬赏
     */
    @Override
    public void payQuestion(int questionid) {
        questionPayDao.payQuestion(questionid);

    }

    /*
     * 设置为优质回答
     */
    @Override
    public void setGoodAnswer(int answerid) {
        questionPayDao.setGoodAnswer(answerid);
    }
}
