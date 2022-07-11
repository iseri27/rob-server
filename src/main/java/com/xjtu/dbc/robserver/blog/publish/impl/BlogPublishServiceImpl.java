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
     * 获取用户tag列表(Tag类，包括id与name）
     */
    @Override
    public List<Tag> getAllTagListByUserId(int myid){
        List<Tag> list = blogPublishDao.selectTagListByUserid(myid);
        return list;
    }

    @Override
    public Integer getTagCount(String t_name, Integer u_id){
        return blogPublishDao.selectTagCntByT_nameAndU_id(t_name, u_id);
    }

    @Override
    public void renameTag(String tagname, Integer u_id, String t_name_new){
        blogPublishDao.updateTagnameByT_nameAndU_id(tagname, u_id, t_name_new);
    }

    @Override
    public void deleteTag(String tagname, Integer u_id){
        blogPublishDao.deleteTagByTagnameAndU_id(tagname, u_id);
    }

    /**
     * 获取新的博客id
     */
    @Override
    public Integer getNewArticleId() {
        return blogPublishDao.getLastId()+1;
    }

    /**
     * 获取新的tagid
     */
    @Override
    public Integer getNewTagId() {
        return blogPublishDao.getLastTagId()+1;
    }

    /**
     * 新增博客
     */
    @Override
    public void addBlog(BlogPublishDto dto) {
        blogPublishDao.addBlog(dto);
    }

    /**
     * 更新博客
     */
    @Override
    public void updateBlogByArticleId(BlogPublishDto dto) {
        blogPublishDao.updateBlogByArticleid(dto);
    }

    /**
     * 为博客添加tag
     */
    @Override
    public void addTagForBlog(Integer myId, BlogPublishDto dto) {
        int t_id;
        int articleid = dto.getArticleid();
        if(dto.getTags()!=null) {
            for (Integer tagId : dto.getTags()) {
                blogPublishDao.addTagForBlog(articleid, tagId);
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

    @Override
    public BlogPublishDto getBlogPublishDtoByArticleId(Integer articleId){
        BlogPublishDto dto = blogPublishDao.selectBlogEditDtoByArticleid(articleId);
        List<String> tagList = blogPublishDao.selectTagListByArtileid(articleId);
//        dto.setTags(tagList);
        return dto;
    }

    @Override
    public Integer getUserStatus(Integer authorId){
        return blogPublishDao.selectUserStatusByAuthorid(authorId);
    }

    @Override
    public Integer getArticleStatus(Integer articleId){
        return blogPublishDao.getArticleStatus(articleId);
    }

}
