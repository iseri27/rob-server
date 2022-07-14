package com.xjtu.dbc.robserver.question.home.impl;

import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.common.page.QueryAction;
import com.xjtu.dbc.robserver.question.answer.dao.QuestionAnswerDao;
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
    public Map<String, Object> getAllQuestionList(PageParam pageParam, int categoryid,int userid) {
        class queryAction implements QueryAction<QuestionHomeListDto> {
            @Override
            public List<QuestionHomeListDto> execute() {
                System.out.println("gggggggggggggggggggggggggg");
                List<QuestionHomeListDto> questionQuestionList = questionHomeDao.getAllQuestionList(categoryid);
                for (QuestionHomeListDto questionHomeListDto: questionQuestionList) {
                    System.out.println("test");
                    questionHomeListDto.setVote_type(questionHomeDao.getVoteTypeByU_A_id(userid,questionHomeListDto.getQuestionid()));
                    questionHomeListDto.setTaglist(questionHomeDao.getTagListByQuestionid(questionHomeListDto.getQuestionid()));
                }
                return questionQuestionList;
            }
        }
        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    @Override
    public Map<String, Object> getNotSolveQuestionList(PageParam pageParam, int categoryid,int userid) {
        class queryAction implements QueryAction<QuestionHomeListDto> {
            @Override
            public List<QuestionHomeListDto> execute() {
                List<QuestionHomeListDto> questionAnswerList = questionHomeDao.getNotSolveQuestionList(categoryid);
                for (QuestionHomeListDto questionHomeListDto: questionAnswerList) {
                    questionHomeListDto.setVote_type(questionHomeDao.getVoteTypeByU_A_id(userid,questionHomeListDto.getQuestionid()));
                    questionHomeListDto.setTaglist(questionHomeDao.getTagListByQuestionid(questionHomeListDto.getQuestionid()));
                }
                return questionAnswerList;
            }
        }
        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    @Override
    public Map<String, Object> getSolveQuestionList(PageParam pageParam, int categoryid,int userid) {
        class queryAction implements QueryAction<QuestionHomeListDto> {
            @Override
            public List<QuestionHomeListDto> execute() {
                System.out.println("gggggggggggggggggggggggggg");
                List<QuestionHomeListDto> questionList = questionHomeDao.getSolveQuestionList(categoryid);
                System.out.println(questionList);
                for (QuestionHomeListDto questionHomeListDto: questionList) {
                    System.out.println("test");
                    questionHomeListDto.setVote_type(questionHomeDao.getVoteTypeByU_A_id(userid,questionHomeListDto.getQuestionid()));
                    questionHomeListDto.setTaglist(questionHomeDao.getTagListByQuestionid(questionHomeListDto.getQuestionid()));
                }
                return questionList;
            }
        }
        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    @Override
    public List<String> getTagListByQuestionid(int questionid) {
        return questionHomeDao.getTagListByQuestionid(questionid);
    }


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
