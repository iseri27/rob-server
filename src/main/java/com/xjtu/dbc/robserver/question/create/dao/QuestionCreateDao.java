package com.xjtu.dbc.robserver.question.create.dao;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.question.create.entity.QuestionPublishDto;

import java.util.List;

public interface QuestionCreateDao {


    /**
     * 添加博客
     * @param questionPublishDto 博客参数
     */
    void addQuestion(QuestionPublishDto questionPublishDto);

    /**
     * 添加回答
     * @param questionPublishDto 博客参数
     */
    void addAnswer(QuestionPublishDto questionPublishDto);

    /**
     * 获取博客的 tag 列表
     * @param articleId 博客 ID
     * @return 该博客的 tag 列表 (tagId)
     */
    List<Integer> getTagListByArticleId(Integer articleId);

    /**
     * 获取用户的 tag 列表
     * @param userId 用户 ID
     * @return tag 列表
     */
    List<Tag> getTagListByUserId(Integer userId);

    /**
     * 新建 tag
     * @param tag tag 参数
     */
    void addTag(Tag tag);

    /**
     * 更新博客
     * @param questionPublishDto 博客参数
     */
    void updateQuestionById(QuestionPublishDto questionPublishDto);

    /**
     * 为博客删除 tag
     * @param articleId 博客 ID
     * @param tagId 要被删除的 tag 的标签
     */
    void deleteTagForQuestion(Integer articleId, Integer tagId);

    /**
     * 为博客添加 tag
     * @param articleId 博客 ID
     * @param tagId tag ID
     */
    void addTagForQuestion(Integer articleId, Integer tagId);

    /**
     * 获取已经存在的待编辑的博客的标题、内容等
     * @param articleId 博客的 ID
     * @return 待编辑的博客的标题、内容等参数
     */
    QuestionPublishDto getQuestionPublishDtoById(Integer articleId);

    /**
     * 获取用户 (作者) 的状态
     * @param authorId 用户 ID
     * @return 用户状态
     */
    Integer getUserStatusByAuthorId(Integer authorId);

    /**
     * 获取博客的状态
     * @param articleId 博客 ID
     * @return 博客状态
     */
    Integer getArticleStatus(Integer articleId);

    /**
     * 获取某用户创建的某名字的 tag 的数量
     * @param tagName tag 名
     * @param userId 用户名
     * @return tag 的数量
     */
    Integer getTagCountByTagNameAndUserId(String tagName, Integer userId);
}
