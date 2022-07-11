package com.xjtu.dbc.robserver.question.home.dao;

import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.question.home.entity.QuestionDetailsDto;
import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;

import java.util.List;

public interface QuestionHomeDao {


    Integer getDislikenumByQuestionId(Integer questionid);

    Integer getLikenumByQuestionId(Integer questionid);

    Integer getCommentNum(Integer questionid);

    List<QuestionHomeListDto> getAllQuestionList(int categoryid);

    List<QuestionHomeListDto> getNotSolveQuestionList(int categoryid);

    List<QuestionHomeListDto> getSolveQuestionList(int categoryid);

    List<String> getTagListByQuestionid(int questionid);

    QuestionDetailsDto getQuestionDetails(int questionid);

    List<Category> getCategory();
}
