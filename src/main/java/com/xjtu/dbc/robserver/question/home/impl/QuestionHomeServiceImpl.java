package com.xjtu.dbc.robserver.question.home.impl;

import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.common.page.QueryAction;
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

    /**
     * 获取悬赏全部列表
     * @param
     * @return 列表
     */
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

    /**
     * 获取未解决悬赏列表
     * @param
     * @return 列表
     */
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

    /**
     * 获取已解决悬赏列表
     * @param
     * @return 列表
     */
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

    /**
     *根据悬赏id获取tag表
     */
    @Override
    public List<String> getTagListByQuestionid(int questionid) {
        return questionHomeDao.getTagListByQuestionid(questionid);
    }

    /**
     * 根据问题ID查询其被踩数
     * @param questionid 问题编号
     * @return 问题被踩次数
     */
    @Override
    public Integer getDislikenumByQuestionId(Integer questionid) {
        return questionHomeDao.getDislikenumByQuestionId(questionid);
    }

    /**
     * 根据问题ID查询其点赞数
     * @param questionid 问题编号
     * @return 问题点赞数
     */
    @Override
    public Integer getLikenumByQuestionId(Integer questionid) {
        return questionHomeDao.getLikenumByQuestionId(questionid);
    }

    /**
     * 根据问题ID查询其评论数
     * @param questionid 问题编号
     * @return 评论数
     */
    @Override
    public Integer getCommentNum(Integer questionid) {
        return questionHomeDao.getCommentNum(questionid);
    }

    /**
     *根据悬赏id获取悬赏及其提问者详情
     */
    @Override
    public QuestionDetailsDto getQuestionDetails(int questionid) {
        return questionHomeDao.getQuestionDetails(questionid);
    }

    /**
     *获取分区列表
     */
    @Override
    public List<Category> getCategory() {
        return questionHomeDao.getCategory();
    }

    /**
     *获取回答数
     */
    @Override
    public Integer getAnswerNum(int questionid) {
        return questionHomeDao.countAnswer(questionid);
    }

    /**
     * 获取搜素悬赏列表(全部，未解决，已解决)
     * @param
     * @return 列表
     */
    @Override
    public Map<String, Object> searchQuestionList(PageParam pageParam, String string, int userid) {
        class queryAction implements QueryAction<QuestionHomeListDto> {
            @Override
            public List<QuestionHomeListDto> execute() {
                List<QuestionHomeListDto> questionList = questionHomeDao.searchQuestionList(string);
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
}
