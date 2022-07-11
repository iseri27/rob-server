package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.blog.publish.entity.BlogPublishDto;
import com.xjtu.dbc.robserver.common.model.tag.Tag;

import java.util.List;

public interface BlogPublishService {
    List<Tag> getAllTagListByUserId(int myid);

    Integer getNewArticleId();

    Integer getNewTagId();

    void addBlog(BlogPublishDto dto);

    void addTagForBlog(Integer myId, BlogPublishDto dto);

    void updateBlogByArticleId(BlogPublishDto dto);

    BlogPublishDto getBlogPublishDtoByArticleId(Integer articleId);

    Integer getUserStatus(Integer authorId);

    Integer getArticleStatus(Integer articleId);

    void addTag(Tag tag);

    Integer getTagCount(String t_name, Integer u_id);

    void renameTag(String tagname, Integer u_id, String t_name_new);

    void deleteTag(String tagname, Integer u_id);
}
