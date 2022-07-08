package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.page.PageParam;

import java.util.List;
import java.util.Map;

public interface BlogHomeService {
    /**
     * 获取用户关注的人的博客
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @param limit 最大数量
     * @return 用户关注的人的博客
     */
    Map<String, Object> getBlogListOfConcernedUser(PageParam pageParam, Integer userId, Integer limit);

    /**
     * 获取分类列表
     * @return 分类列表
     */
    List<Category> getCategoryList();
}
