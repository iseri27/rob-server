package com.xjtu.dbc.robserver.blog.home.impl;

import com.xjtu.dbc.robserver.blog.home.BlogHomeService;
import com.xjtu.dbc.robserver.blog.home.dao.BlogHomeDao;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.common.page.QueryAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service @Transactional
public class BlogHomeServiceImpl implements BlogHomeService {
    @Resource
    private BlogHomeDao blogHomeDao;

    /**
     * 获取用户及其关注的用户的博客
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @param limit  最大数量
     * @return 用户及其关注的用户的博客
     */
    @Override
    public Map<String, Object> getBlogList(PageParam pageParam, Integer userId, Integer limit) {
        class queryAction implements QueryAction<Article> {
            @Override
            public List<Article> execute() {
                List<Article> blogList = blogHomeDao.getUserRelatedArticleList(userId, Constants.ARTICLE_TYPE_BLOG, limit);
                return blogList;
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }
}
