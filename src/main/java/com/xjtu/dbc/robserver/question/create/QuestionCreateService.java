package com.xjtu.dbc.robserver.question.create;

import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;

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
    void findQuestionById(int questionId);

    /**
     * 保存草稿箱的草稿
     * @param questionCreateDto {悬赏ID,悬赏内容, 时间, 用户ID,Tag列表,悬赏易拉罐数量 }
     * @return 悬赏的 ID
     */
    void saveQuestion(QuestionCreateDto questionCreateDto);
}
