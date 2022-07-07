package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.common.model.article.Article;

import java.util.List;

public interface BlogHomeService {
    /**
     * 获取用户及其关注的用户的博客
     * @param userId 用户 ID
     * @param limit 最大数量
     * @return 用户及其关注的用户的博客
     */
    List<Article> getBlogList(Integer userId, Integer limit);
}
