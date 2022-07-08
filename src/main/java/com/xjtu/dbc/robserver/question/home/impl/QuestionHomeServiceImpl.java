package com.xjtu.dbc.robserver.question.home.impl;

import com.xjtu.dbc.robserver.question.home.QuestionHomeService;
import com.xjtu.dbc.robserver.question.home.dao.QuestionHomeDao;
import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service @Transactional
public class QuestionHomeServiceImpl implements QuestionHomeService {

    @Resource
    private QuestionHomeDao questionHomeDao;


    @Override
    public List<QuestionHomeListDto> getAllQuestionList() {
        return questionHomeDao.getAllQuestionList();
    }

    @Override
    public List<QuestionHomeListDto> getNotSolveQuestionList() {
        return questionHomeDao.getNotSolveQuestionList();
    }

    @Override
    public List<QuestionHomeListDto> getSolveQuestionList() {
        return questionHomeDao.getSolveQuestionList();
    }

    @Override
    public List<String> getTagListByQuestionid(int questionid) {
        return questionHomeDao.getTagListByQuestionid(questionid);
    }

//    @Override
//    public Map<String, Object> getQuestionList(QuestionPreviewDto dto) {
//        return null;
//    }

    @Override
    public Integer getDislikenumByQuestionId(Integer questionid) {
        return questionHomeDao.getDislikenumByQuestionId(questionid);
    }

    @Override
    public Integer getLikenumByQuestionId(Integer questionid) {
        System.out.println(3333333);
        return questionHomeDao.getLikenumByQuestionId(questionid);
    }

    @Override
    public Integer getCommentNum(Integer questionid) {
        return questionHomeDao.getCommentNum(questionid);
    }
}
