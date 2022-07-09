package com.xjtu.dbc.robserver.blog.publish.dao;

import com.xjtu.dbc.robserver.blog.detail.BlogDetailDto;
import com.xjtu.dbc.robserver.blog.publish.BlogEditDto;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.model.user.User;

import java.util.List;

public interface BlogPublishDao {

    int getLastId();
    
    int getLastTagId();
    
    void addBlog(BlogEditDto dto);
    
    List<String> selectTagListByArtileid(int articleid);
    
    int selectTagidUseTagname(int myid, String tagname);
    
    List<Tag> selectTagListByUserid(int myid);

    void updateBlogByArticleid(BlogEditDto dto);

    void addTagForBlog(int articleid, int tagid);

    void dropTagForBlog(int articleid, int tagid);

    BlogEditDto selectBlogEditDtoByArticleid(int articleid);

    int selectUserStatusByAuthorid(int authorid);

    int getArticleStatus(int articleid);

    void addTag(Tag tag);

    Integer selectTagCntByT_nameAndU_id(String t_name, int u_id);

    void updateTagnameByT_nameAndU_id(String tagname, int u_id, String t_name_new);

    void deleteTagByTagnameAndU_id(String tagname, int u_id);
}
