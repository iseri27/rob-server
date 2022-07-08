package com.xjtu.dbc.robserver.blog.home.dao;

import com.xjtu.dbc.robserver.common.model.article.Article;

import java.util.List;

public interface BlogHomeDao {
    /**
     * 获取用户及其关注的用户的博客
     * @param userId 用户 ID
     * @param articleType 文本类型
     * @param limit  最大数量
     * @return 用户及其关注的用户的博客
     */
    List<Article> getUserRelatedArticleList(Integer userId, Integer articleType, Integer limit);
}
