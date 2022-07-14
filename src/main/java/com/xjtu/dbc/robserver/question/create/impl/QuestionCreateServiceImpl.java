package com.xjtu.dbc.robserver.question.create.impl;


import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.question.create.QuestionCreateService;
import com.xjtu.dbc.robserver.question.create.dao.QuestionCreateDao;
import com.xjtu.dbc.robserver.question.create.entity.QuestionPublishDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional
public class QuestionCreateServiceImpl implements QuestionCreateService {

    @Resource
    private QuestionCreateDao questionCreateDao;

    /**
     * 通过用户 ID 获取所有的 tag 列表
     * @param myId 用户 ID
     * @return Tag 列表
     */
    @Override
    public List<Tag> getAllTagListByUserId(Integer myId){
        List<Tag> list = questionCreateDao.getTagListByUserId(myId);
        return list;
    }

    /**
     * 新建博客
     * @param questionPublishDto 博客参数
     */
    @Override
    public void addQuestion(QuestionPublishDto questionPublishDto) {
        questionCreateDao.addQuestion(questionPublishDto);
    }

    /**
     * 新建回答
     * @param questionPublishDto 博客参数
     */
    @Override
    public void addAnswer(QuestionPublishDto questionPublishDto) {
        questionCreateDao.addAnswer(questionPublishDto);
    }

    /**
     * 为博客添加 Tag
     * @param myId 用户 ID
     * @param questionPublishDto 博客参数
     */
    @Override
    public void addTagForQuestion(Integer myId, QuestionPublishDto questionPublishDto) {
        Integer articleId = questionPublishDto.getArticleid();
        if(questionPublishDto.getTags() != null) {
            for (Integer tagId : questionPublishDto.getTags()) {
                questionCreateDao.addTagForQuestion(articleId, tagId);
            }
        }
    }

    /**
     * 更新博客
     * @param dto 博客参数
     */
    @Override
    public void updateQuestionByArticleId(QuestionPublishDto dto) {
        questionCreateDao.updateQuestionById(dto);
    }

    /**
     * 获取已存在的博客的参数
     * @param articleId 博客 ID
     * @return 博客的各项参数
     */
    @Override
    public QuestionPublishDto getQuestionPublishDtoByArticleId(Integer articleId){
        QuestionPublishDto dto = questionCreateDao.getQuestionPublishDtoById(articleId);
        List<Integer> tagList = questionCreateDao.getTagListByArticleId(articleId);
        dto.setTags(tagList);
        return dto;
    }

    /**
     * 获取用户的状态
     * @param authorId 用户 ID
     * @return 用户状态
     */
    @Override
    public Integer getUserStatus(Integer authorId){
        return questionCreateDao.getUserStatusByAuthorId(authorId);
    }

    /**
     * 获取博客的状态
     * @param articleId 博客 ID
     * @return 博客状态
     */
    @Override
    public Integer getArticleStatus(Integer articleId){
        return questionCreateDao.getArticleStatus(articleId);
    }

    /**
     * 新增 Tag
     * @param tag
     */
    @Override
    public void addTag(Tag tag){
        questionCreateDao.addTag(tag);
    }

    /**
     * 查询拥有该名字的 tag 的数量
     * @param tagName tag 的名字
     * @param userId 用户 ID
     * @return tag 的数量
     */
    @Override
    public Integer getTagCount(String tagName, Integer userId){
        return questionCreateDao.getTagCountByTagNameAndUserId(tagName, userId);
    }

    /**
     * 更新博客的 tag
     * @param tags      标签列表
     * @param articleId 博客 ID
     */
    @Override
    public void updateQuestionTag(List<Integer> tags, Integer articleId) {

        // 删除旧的 tag
        List<Integer> tagListPre = questionCreateDao.getTagListByArticleId(articleId);

        log.info("Old Tags : " + tagListPre);

        for (Integer tagId: tagListPre) {
            if (!tags.contains(tagId)) {
                questionCreateDao.deleteTagForQuestion(articleId, tagId);
            }
        }

        log.info("New Tags : " + tags);
        // 添加新的 tag
        for (Integer tagId: tags) {
            if (!tagListPre.contains(tagId)) {
                questionCreateDao.addTagForQuestion(articleId, tagId);
            }
        }
    }

}
