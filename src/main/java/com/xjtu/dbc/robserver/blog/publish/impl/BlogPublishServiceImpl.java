package com.xjtu.dbc.robserver.blog.publish.impl;


import com.xjtu.dbc.robserver.blog.publish.entity.BlogPublishDto;

import com.xjtu.dbc.robserver.blog.publish.BlogPublishService;
import com.xjtu.dbc.robserver.blog.publish.dao.BlogPublishDao;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class BlogPublishServiceImpl implements BlogPublishService {

    @Resource
    private BlogPublishDao blogPublishDao;

    /**
     * 通过用户 ID 获取所有的 tag 列表
     * @param myId 用户 ID
     * @return Tag 列表
     */
    @Override
    public List<Tag> getAllTagListByUserId(Integer myId){
        List<Tag> list = blogPublishDao.getTagListByUserId(myId);
        return list;
    }

    /**
     * 新建博客
     * @param blogPublishDto 博客参数
     */
    @Override
    public void addBlog(BlogPublishDto blogPublishDto) {
        blogPublishDao.addBlog(blogPublishDto);
    }

    /**
     * 为博客添加 Tag
     * @param myId 用户 ID
     * @param blogPublishDto 博客参数
     */
    @Override
    public void addTagForBlog(Integer myId, BlogPublishDto blogPublishDto) {
        Integer articleId = blogPublishDto.getArticleid();
        if(blogPublishDto.getTags() != null) {
            for (Integer tagId : blogPublishDto.getTags()) {
                blogPublishDao.addTagForBlog(articleId, tagId);
            }
        }
    }

    /**
     * 更新博客
     * @param dto 博客参数
     */
    @Override
    public void updateBlogByArticleId(BlogPublishDto dto) {
        blogPublishDao.updateBlogById(dto);
    }

    /**
     * 获取已存在的博客的参数
     * @param articleId 博客 ID
     * @return 博客的各项参数
     */
    @Override
    public BlogPublishDto getBlogPublishDtoByArticleId(Integer articleId){
        BlogPublishDto dto = blogPublishDao.getBlogPublishDtoById(articleId);
        List<Integer> tagList = blogPublishDao.getTagListByArticleId(articleId);
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
        return blogPublishDao.getUserStatusByAuthorId(authorId);
    }

    /**
     * 获取博客的状态
     * @param articleId 博客 ID
     * @return 博客状态
     */
    @Override
    public Integer getArticleStatus(Integer articleId){
        return blogPublishDao.getArticleStatus(articleId);
    }

    /**
     * 新增 Tag
     * @param tag
     */
    @Override
    public void addTag(Tag tag){
        blogPublishDao.addTag(tag);
    }

    /**
     * 查询拥有该名字的 tag 的数量
     * @param tagName tag 的名字
     * @param userId 用户 ID
     * @return tag 的数量
     */
    @Override
    public Integer getTagCount(String tagName, Integer userId){
        return blogPublishDao.getTagCountByTagNameAndUserId(tagName, userId);
    }

    /**
     * 更新博客的 tag
     * @param tags      标签列表
     * @param articleId 文章 ID
     */
    @Override
    public void updateBlogTag(List<Integer> tags, Integer articleId) {

    }
}
