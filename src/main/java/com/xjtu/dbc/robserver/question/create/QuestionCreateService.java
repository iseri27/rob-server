package com.xjtu.dbc.robserver.question.create;


import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.question.create.entity.QuestionPublishDto;

import java.util.List;

public interface QuestionCreateService {

    /**
     * 通过用户 ID 获取所有的 tag 列表
     * @param myId 用户 ID
     * @return Tag 列表
     */
    List<Tag> getAllTagListByUserId(Integer myId);

    /**
     * 新建博客
     * @param questionPublishDto 博客参数
     */
    void addQuestion(QuestionPublishDto questionPublishDto);

    /**
     * 新建回答
     * @param questionPublishDto 博客参数
     */
    void addAnswer(QuestionPublishDto questionPublishDto);

    /**
     * 为博客添加 Tag
     * @param myId 用户 ID
     * @param questionPublishDto 博客参数
     */
    void addTagForQuestion(Integer myId, QuestionPublishDto questionPublishDto);

    /**
     * 更新博客
     * @param dto 博客参数
     */
    void updateQuestionByArticleId(QuestionPublishDto dto);

    /**
     * 获取已存在的博客的参数
     * @param articleId 博客 ID
     * @return 博客的各项参数
     */
    QuestionPublishDto getQuestionPublishDtoByArticleId(Integer articleId);

    /**
     * 获取用户的状态
     * @param authorId 用户 ID
     * @return 用户状态
     */
    Integer getUserStatus(Integer authorId);

    /**
     * 获取博客的状态
     * @param articleId 博客 ID
     * @return 博客状态
     */
    Integer getArticleStatus(Integer articleId);

    /**
     * 新增 Tag
     * @param tag
     */
    void addTag(Tag tag);

    /**
     * 查询拥有该名字的 tag 的数量
     * @param tagName tag 的名字
     * @param userId 用户 ID
     * @return tag 的数量
     */
    Integer getTagCount(String tagName, Integer userId);

    /**
     * 更新博客的 tag
     * @param tags 标签列表
     * @param articleId 博客各项参数
     */
    void updateQuestionTag(List<Integer> tags, Integer articleId);
}
