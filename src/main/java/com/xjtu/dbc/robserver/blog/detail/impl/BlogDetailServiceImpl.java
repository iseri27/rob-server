package com.xjtu.dbc.robserver.blog.detail.impl;

import com.xjtu.dbc.robserver.blog.detail.BlogDetailDto;
import com.xjtu.dbc.robserver.blog.detail.BlogDetailService;
import com.xjtu.dbc.robserver.blog.detail.CurrentUserDto;
import com.xjtu.dbc.robserver.blog.detail.dao.BlogDetailDao;
import com.xjtu.dbc.robserver.blog.publish.dao.BlogPublishDao;
import com.xjtu.dbc.robserver.common.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BlogDetailServiceImpl implements BlogDetailService {
    @Resource
    private BlogDetailDao blogDetailDao;
    /**
     * 获取博客详情
     */
    @Override
    public BlogDetailDto getBlogDetailByArticleid(int articleid){
        BlogDetailDto dto = blogDetailDao.selectBlogDetailDtoByArticleid(articleid);
        List<String> tagList = blogDetailDao.selectTagListByArtileid(articleid);
        dto.setTags(tagList);
        User author = blogDetailDao.selectAuthorByArtileid(articleid);
        dto.setAuthorname(author.getUsername());
        dto.setAuthoravatar(author.getUseravatar());
        dto.setCategoryname(blogDetailDao.selectCategorynameByArtileid(articleid));
        return dto;
    }

    @Override
    public CurrentUserDto getCurrentUser(Integer myid){
        return blogDetailDao.getCurrentUser(myid);
    }
}
