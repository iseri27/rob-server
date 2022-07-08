package com.xjtu.dbc.robserver.blog.home.dao;

import com.xjtu.dbc.robserver.common.model.article.BlogVO;
import com.xjtu.dbc.robserver.common.model.category.Category;

import java.util.List;

public interface BlogHomeDao {

    /**
     * 获取用户关注的人的博客
     * @param userId 用户 ID
     * @param limit 最大数量
     * @return 用户关注的人的博客
     */
    List<BlogVO> getArticleListOfConcernedUser(Integer userId, Integer articleType, Integer limit);

    /**
     * 获取分类列表
     * @return 分类列表
     */
    List<Category> getCategoryList();
}
