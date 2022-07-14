package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.blog.home.entity.BlogDto;
import com.xjtu.dbc.robserver.blog.home.entity.BlogVO;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.page.PageParam;

import java.util.List;
import java.util.Map;

public interface BlogHomeService {
    /**
     * 获取用户关注的人的博客
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @return 用户关注的人的博客
     */
    Map<String, Object> getBlogListOfConcernedUser(PageParam pageParam, Integer userId);

    /**
     * 获取我的博客列表
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @return 我的博客列表
     */
    Map<String, Object> getMyBlogList(PageParam pageParam, Integer userId);

    /**
     * 获取推荐的的博客列表
     * @param blogDto 分页参数
     * @param userId 用户 ID
     * @return 推荐的的博客列表
     */
    Map<String, Object> getRecommendBlogList(BlogDto blogDto, Integer userId);

    /**
     * 获取分类列表
     * @return 分类列表
     */
    List<Category> getCategoryList();

    /**
     * 获取所有博客的列表
     * @param userId 用户 ID
     * @return 博客列表
     */
    List<BlogVO> getRssBlogList(Integer userId);
}
