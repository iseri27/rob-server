package com.xjtu.dbc.robserver.blog.home.impl;

import com.xjtu.dbc.robserver.blog.home.BlogHomeService;
import com.xjtu.dbc.robserver.blog.home.dao.BlogHomeDao;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.model.article.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service @Transactional
public class BlogHomeServiceImpl implements BlogHomeService {
    @Resource
    private BlogHomeDao blogHomeDao;

    /**
     * 获取用户及其关注的用户的博客
     * @param userId 用户 ID
     * @param limit  最大数量
     * @return 用户及其关注的用户的博客
     */
    @Override
    public List<Article> getBlogList(Integer userId, Integer limit) {
        return blogHomeDao.getUserRelatedArticleList(userId, Constants.ARTICLE_TYPE_BLOG, limit);
    }
}
