package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.blog.publish.entity.BlogPublishDto;
import com.xjtu.dbc.robserver.common.model.tag.Tag;

import java.util.List;

public interface BlogPublishService {
    /**
     * 通过用户 ID 获取所有的 tag 列表
     * @param myId 用户 ID
     * @return Tag 列表
     */
    List<Tag> getAllTagListByUserId(Integer myId);

    /**
     * 新建博客
     * @param blogPublishDto 博客参数
     */
    void addBlog(BlogPublishDto blogPublishDto);

    /**
     * 为博客添加 Tag
     * @param myId 用户 ID
     * @param blogPublishDto 博客参数
     */
    void addTagForBlog(Integer myId, BlogPublishDto blogPublishDto);

    /**
     * 更新博客
     * @param dto 博客参数
     */
    void updateBlogByArticleId(BlogPublishDto dto);

    /**
     * 获取已存在的博客的参数
     * @param articleId 博客 ID
     * @return 博客的各项参数
     */
    BlogPublishDto getBlogPublishDtoByArticleId(Integer articleId);

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
}
