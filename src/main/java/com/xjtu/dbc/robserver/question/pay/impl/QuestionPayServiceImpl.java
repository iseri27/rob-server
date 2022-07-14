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

    @Override
    public int ifFirst(int questionid) {
        return questionPayDao.countGoodAnswer(questionid);
    }

    @Override
    public void payQuestion(int questionid) {
        questionPayDao.payQuestion(questionid);

    }

    @Override
    public void setGoodAnswer(int answerid) {
        questionPayDao.setGoodAnswer(answerid);
    }
}
