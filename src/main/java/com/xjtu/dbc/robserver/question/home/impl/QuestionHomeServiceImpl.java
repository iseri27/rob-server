package com.xjtu.dbc.robserver.question.home.impl;

import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.question.home.QuestionHomeService;
import com.xjtu.dbc.robserver.question.home.dao.QuestionHomeDao;
import com.xjtu.dbc.robserver.question.home.entity.QuestionDetailsDto;
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
    public List<QuestionHomeListDto> getAllQuestionList(int categoryid) {
        return questionHomeDao.getAllQuestionList(categoryid);
    }

    @Override
    public List<QuestionHomeListDto> getNotSolveQuestionList(int categoryid) {
        return questionHomeDao.getNotSolveQuestionList(categoryid);
    }

    @Override
    public List<QuestionHomeListDto> getSolveQuestionList(int categoryid) {
        return questionHomeDao.getSolveQuestionList(categoryid);
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
        return questionHomeDao.getLikenumByQuestionId(questionid);
    }

    @Override
    public Integer getCommentNum(Integer questionid) {
        return questionHomeDao.getCommentNum(questionid);
    }

    @Override
    public QuestionDetailsDto getQuestionDetails(int questionid) {
        return questionHomeDao.getQuestionDetails(questionid);
    }

    @Override
    public List<Category> getCategory() {
        return questionHomeDao.getCategory();
    }

    @Override
    public Integer getAnswerNum(int questionid) {
        return questionHomeDao.countAnswer(questionid);
    }
}
