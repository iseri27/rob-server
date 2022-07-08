package com.xjtu.dbc.robserver.blog.publish;

import com.xjtu.dbc.robserver.common.model.tag.Tag;

import java.util.List;

public interface BlogPublishService {
    List<Tag> getAllTagListByUserid(int myid);

    int getNewArticleid();

    int getNewTagid();

    void addBlog(BlogEditDto dto);

    void addTagForBlog(int myid,BlogEditDto dto);

    void updateBlogByArticleid(BlogEditDto dto);

    void updateBlogTag(int myid, BlogEditDto dto);

    void addTag(int myid, BlogEditDto dto);

    BlogDetailDto getBlogDetailByArticleid(int articleid);

    BlogEditDto getBlogEditDtoByArticleid(int articleid);

    int getUserStatus(int authorid);
}
