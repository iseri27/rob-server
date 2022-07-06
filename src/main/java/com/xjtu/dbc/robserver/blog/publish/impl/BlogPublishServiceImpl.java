package com.xjtu.dbc.robserver.blog.publish.impl;

import com.xjtu.dbc.robserver.blog.publish.BlogEditDto;
import com.xjtu.dbc.robserver.blog.publish.BlogPublishService;
import com.xjtu.dbc.robserver.blog.publish.dao.BlogPublishDao;
import com.xjtu.dbc.robserver.common.model.article.Article;
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
     * 获取用户tag列表(tagid）
     */
    @Override
    public List<Tag> getAllTagListByUserid(int myid){
        List<Tag> list = blogPublishDao.selectTagListByUserid(myid);
        return list;
    }
    /**
     * 获取新的博客id
     */
    @Override
    public int getNewArticleid() {
        return blogPublishDao.getLastId()+1;
    }

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
        for (String tagname: dto.getTags()) {
            t_id=blogPublishDao.selectTagidUseTagname(myid,tagname);
            blogPublishDao.addTagForBlog(articleid, t_id);
        }
    }



    @Override
    public void addTag(int myid, BlogEditDto dto){
        for (String t_id: dto.getTags()) {
            blogPublishDao.addTag(getNewTagid(), myid, t_id);
        }
    }

    /**
     * 更新博客的tag
     */
    @Override
    public void updateBlogTag(int myid, BlogEditDto dto) {
        //  获取之前的tag列表
        int articleid = dto.getArticleid();
        int t_id;
        List<String> myList = blogPublishDao.selectTagnameListByUserid(myid);
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
            if(!myList.contains(tagname2)){
                blogPublishDao.addTag(getNewTagid(), myid, tagname2);
            }
            if (!oldList.contains(tagname2)) {
                t_id=blogPublishDao.selectTagidUseTagname(myid,tagname2);
                blogPublishDao.addTagForBlog(articleid, t_id);
            }
        }
    }

    @Override
    public Article getBlogDetailByArticleid(int articleid){
        return blogPublishDao.selectBlogDetailByArticleid(articleid);
    }

}
