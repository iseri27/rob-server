package com.xjtu.dbc.robserver.blog.home.impl;

import com.xjtu.dbc.robserver.blog.home.BlogHomeService;
import com.xjtu.dbc.robserver.blog.home.dao.BlogHomeDao;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
import com.xjtu.dbc.robserver.common.model.article.BlogVO;
import com.xjtu.dbc.robserver.common.model.category.Category;
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
     * 获取用户关注的人的博客
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @param limit 最大数量
     * @return 用户关注的人的博客
     */
    @Override
    public Map<String, Object> getBlogListOfConcernedUser(PageParam pageParam, Integer userId, Integer limit) {
        class queryAction implements QueryAction<BlogVO> {
            @Override
            public List<BlogVO> execute() {
                return blogHomeDao.getArticleListOfConcernedUser(userId, Constants.ARTICLE_TYPE_BLOG, limit);
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    /**
     * 获取分类列表
     * @return 分类列表
     */
    @Override
    public List<Category> getCategoryList() {
        return blogHomeDao.getCategoryList();
    }
}
