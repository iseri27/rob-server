package com.xjtu.dbc.robserver.blog.home.dao;

import com.xjtu.dbc.robserver.blog.home.entity.BlogVO;
import com.xjtu.dbc.robserver.common.model.category.Category;

import java.util.List;

public interface BlogHomeDao {

    /**
     * 获取用户关注的人的博客
     * @param userId 用户 ID
     * @return 用户关注的人的博客
     */
    List<BlogVO> getArticleListOfConcernedUser(Integer userId, Integer articleType);

    /**
     * 获取用户自己的博客
     * @param userId 用户 ID
     * @return 用户自己的博客
     */
    List<BlogVO> getArticleListOfMyself(Integer userId, Integer articleType);

    /**
     * 获取推荐的博客
     * @param userId 用户 ID
     * @return 用推荐的博客
     */
    List<BlogVO> getArticleListOfRecommend(Integer userId, Integer categoryId, int articleType);

    /**
     * 获取分类列表
     * @return 分类列表
     */
    List<Category> getCategoryList();

    /**
     * 获取文本的 Tag 列表
     * @param articleId 文本 ID
     * @return Tag 列表
     */
    List<String> getTagListOfArticle(Integer articleId);
}
