package com.xjtu.dbc.robserver.question.create;

import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagListDto;

import java.util.List;

public interface QuestionCreateService {

    /**
     * 发布悬赏
     * @param questionCreateDto {悬赏ID,悬赏内容, 时间, 用户ID,Tag列表,悬赏易拉罐数量 }
     * @return 悬赏的 ID
     */
    void createQuestion(QuestionCreateDto questionCreateDto);


    /**
     * 修改悬赏
     * @param questionCreateDto {悬赏ID,悬赏内容, 时间, 用户ID,Tag列表,悬赏易拉罐数量 }
     * @return 悬赏的 ID
     */
    void modifyQuestion(QuestionCreateDto questionCreateDto);

    /**
     * 按Id查找悬赏
     * @param questionId {问题ID}
     * @return 悬赏的 ID
     */
    QuestionCreateDto findQuestionById(int questionId);

    /**
     * 保存草稿箱的草稿
     * @param questionCreateDto {悬赏ID,悬赏内容, 时间, 用户ID,Tag列表,悬赏易拉罐数量 }
     * @return 悬赏的 ID
     */
    void saveQuestion(QuestionCreateDto questionCreateDto);

    /**
     * 删除草稿箱的草稿
     * @param questionid 问题id
     */
    void deleteQuestionById(int questionid);

    /**
     * 删除草稿箱的草稿
     * @param userid 当前用户id
     */
    int selectTagNum(int userid);

    /**
     *为悬赏添加标签
     */
    void connectTag(QuestionTagDto questionTagDto);

    /**
     *删除悬赏标签
     */
    void disconnectTag(QuestionTagDto questionTagDto);

    /**
     *获取当前悬赏的标签数量
     */
    int getQuestionTagNum(int questionid);

    /**
     *根据悬赏id获取tag表
     */
    List<QuestionTagListDto> getQuestionTagListById(int questionid);
}
