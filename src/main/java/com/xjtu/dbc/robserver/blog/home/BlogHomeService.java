package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.common.page.PageParam;

import java.util.Map;

public interface BlogHomeService {
    /**
     * 获取用户及其关注的用户的博客
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @param limit 最大数量
     * @return 用户及其关注的用户的博客
     */
    Map<String, Object> getBlogList(PageParam pageParam, Integer userId, Integer limit);
}
