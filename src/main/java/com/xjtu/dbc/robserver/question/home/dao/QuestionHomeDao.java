package com.xjtu.dbc.robserver.question.home.dao;

import com.xjtu.dbc.robserver.question.home.entity.QuestionDetailsDto;
import com.xjtu.dbc.robserver.question.home.entity.QuestionHomeListDto;

import java.util.List;

public interface QuestionHomeDao {


    Integer getDislikenumByQuestionId(Integer questionid);

    Integer getLikenumByQuestionId(Integer questionid);

    Integer getCommentNum(Integer questionid);

    List<QuestionHomeListDto> getAllQuestionList();

    List<QuestionHomeListDto> getNotSolveQuestionList();

    List<QuestionHomeListDto> getSolveQuestionList();

    List<String> getTagListByQuestionid(int questionid);

    QuestionDetailsDto getQuestionDetails(int questionid);
}
