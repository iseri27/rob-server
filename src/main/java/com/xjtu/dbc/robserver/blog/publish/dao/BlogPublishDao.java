package com.xjtu.dbc.robserver.blog.publish.dao;

import com.xjtu.dbc.robserver.blog.publish.entity.BlogPublishDto;
import com.xjtu.dbc.robserver.common.model.tag.Tag;

import java.util.List;

public interface BlogPublishDao {

    /**
     * 添加博客
     * @param dto
     */
    void addBlog(BlogPublishDto dto);
    
    List<String> selectTagListByArticleId(Integer articleId);
    
    List<Tag> selectTagListByUserId(Integer myId);

    void updateBlogByArticleId(BlogPublishDto blogPublishDto);

    void addTagForBlog(Integer articleId, Integer tagId);

    BlogPublishDto selectBlogEditDtoByArticleId(Integer articleId);

    int selectUserStatusByAuthorId(Integer authorId);

    int getArticleStatus(Integer articleId);

    void addTag(Tag tag);

    Integer selectTagCountByTagNameAndUserId(String tagName, Integer userId);
}
