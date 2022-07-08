package com.xjtu.dbc.robserver.blog.publish.impl;

import com.xjtu.dbc.robserver.blog.publish.BlogDetailDto;
import com.xjtu.dbc.robserver.blog.publish.BlogEditDto;
import com.xjtu.dbc.robserver.blog.publish.BlogPublishService;
import com.xjtu.dbc.robserver.blog.publish.dao.BlogPublishDao;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.common.model.user.User;
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
     * 获取用户tag列表(Tag类，包括id与name）
     */
    @Override
    public List<Tag> getAllTagListByUserid(int myid){
        List<Tag> list = blogPublishDao.selectTagListByUserid(myid);
        return list;
    }

    @Override
    public Integer getTagCount(String t_name, int u_id){
        return blogPublishDao.selectTagCntByT_nameAndU_id(t_name, u_id);
    }

    @Override
    public void renameTag(String tagname, int u_id, String t_name_new){
        blogPublishDao.updateTagnameByT_nameAndU_id(tagname, u_id, t_name_new);
    }

    @Override
    public void deleteTag(String tagname, int u_id){
        blogPublishDao.deleteTagByTagnameAndU_id(tagname, u_id);
    }


    /**
     * 获取新的博客id
     */
    @Override
    public int getNewArticleid() {
        return blogPublishDao.getLastId()+1;
    }

    /**
     * 获取新的tagid
     */
    @Override
    public int getNewTagid() {
        return blogPublishDao.getLastTagId()+1;
    }

    /**
     * 新增博客
     */
    @Override
    public void addBlog(BlogEditDto dto) {
        blogPublishDao.addBlog(dto);
    }

    /**
     * 更新博客
     */
    @Override
    public void updateBlogByArticleid(BlogEditDto dto) {
        blogPublishDao.updateBlogByArticleid(dto);
    }

    /**
     * 为博客添加tag
     */
    @Override
    public void addTagForBlog(int myid,BlogEditDto dto) {
        int t_id;
        int articleid = dto.getArticleid();
        if(dto.getTags()!=null) {
            for (String tagname : dto.getTags()) {
                t_id = blogPublishDao.selectTagidUseTagname(myid, tagname);
                blogPublishDao.addTagForBlog(articleid, t_id);
            }
        }
    }


    /**
     * 增加我的tag
     */
    @Override
    public void addTag(Tag tag){
        blogPublishDao.addTag(tag);
    }

    /**
     * 更新博客的tag
     */
    @Override
    public void updateBlogTag(int myid, BlogEditDto dto) {
        //  获取之前的tag列表
        int articleid = dto.getArticleid();
        int t_id;
        List<String> oldList = blogPublishDao.selectTagListByArtileid(articleid);
        List<String> newList = dto.getTags();

        // 去除需要删除的tag
        for (String tagname1: oldList) {
            if (!newList.contains(tagname1)) {
                t_id=blogPublishDao.selectTagidUseTagname(myid,tagname1);
                blogPublishDao.dropTagForBlog(articleid, t_id);
            }
        }

        // 新增之前没有的tag
        for (String tagname2: newList) {
            if (!oldList.contains(tagname2)) {
                t_id=blogPublishDao.selectTagidUseTagname(myid,tagname2);
                blogPublishDao.addTagForBlog(articleid, t_id);
            }
        }
    }

    /**
     * 获取博客详情
     */
    @Override
    public BlogDetailDto getBlogDetailByArticleid(int articleid){
        BlogDetailDto dto = blogPublishDao.selectBlogDetailDtoByArticleid(articleid);
        List<String> tagList = blogPublishDao.selectTagListByArtileid(articleid);
        dto.setTags(tagList);
        User author = blogPublishDao.selectAuthorByArtileid(articleid);
        dto.setAuthorname(author.getUsername());
        dto.setAuthoravatar(author.getUseravatar());
        return dto;
    }

    @Override
    public BlogEditDto getBlogEditDtoByArticleid(int articleid){
        BlogEditDto dto = blogPublishDao.selectBlogEditDtoByArticleid(articleid);
        List<String> tagList = blogPublishDao.selectTagListByArtileid(articleid);
        dto.setTags(tagList);
        return dto;
    }

    @Override
    public int getUserStatus(int authorid){
        return blogPublishDao.selectUserStatusByAuthorid(authorid);
    }

    @Override
    public int getArticleStatus(int articleid){
        return blogPublishDao.getArticleStatus(articleid);
    }

}
