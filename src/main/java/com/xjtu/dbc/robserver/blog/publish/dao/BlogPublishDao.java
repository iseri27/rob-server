package com.xjtu.dbc.robserver.blog.publish.dao;

import com.xjtu.dbc.robserver.blog.publish.BlogDetailDto;
import com.xjtu.dbc.robserver.blog.publish.BlogEditDto;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.model.user.User;

import java.util.List;

public interface BlogPublishDao {

    int getLastId();
    
    int getLastTagId();
    
    void addBlog(BlogEditDto dto);
    
    List<String> selectTagListByArtileid(int articleid);
    
    List<String> selectTagnameListByUserid(int myid);
    
    int selectTagidUseTagname(int myid, String tagname);
    
    List<Tag> selectTagListByUserid(int myid);

    void updateBlogByArticleid(BlogEditDto dto);

    void addTagForBlog(int articleid, int tagid);

    void dropTagForBlog(int articleid, int tagid);

    void addTag(int tagid, int myid, String t_id);

    BlogDetailDto selectBlogDetailDtoByArticleid(int articleid);

    BlogEditDto selectBlogEditDtoByArticleid(int articleid);

    User selectAuthorByArtileid(int articleid);

    int selectUserStatusByAuthorid(int authorid);
}
