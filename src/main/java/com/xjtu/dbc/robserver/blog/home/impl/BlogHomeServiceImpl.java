package com.xjtu.dbc.robserver.blog.home.impl;

import com.xjtu.dbc.robserver.blog.home.BlogHomeService;
import com.xjtu.dbc.robserver.blog.home.dao.BlogHomeDao;
import com.xjtu.dbc.robserver.blog.home.entity.BlogDto;
import com.xjtu.dbc.robserver.blog.home.entity.BlogVO;
import com.xjtu.dbc.robserver.common.Constants;
import com.xjtu.dbc.robserver.common.Utils;
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
     * @return 用户关注的人的博客
     */
    @Override
    public Map<String, Object> getBlogListOfConcernedUser(PageParam pageParam, Integer userId) {
        class queryAction implements QueryAction<BlogVO> {
            @Override
            public List<BlogVO> execute() {
                List<BlogVO> blogVOList = blogHomeDao.getArticleListOfConcernedUser(userId, Constants.ARTICLE_TYPE_BLOG);

                for (BlogVO blogVO: blogVOList) {
                    blogVO.setTagList(blogHomeDao.getTagListOfArticle(blogVO.getArticleid()));
                }

                return blogVOList;
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    /**
     * 获取我的博客列表
     * @param pageParam 分页参数
     * @param userId 用户 ID
     * @return 我的博客列表
     */
    @Override
    public Map<String, Object> getMyBlogList(PageParam pageParam, Integer userId) {
        class queryAction implements  QueryAction<BlogVO> {
            @Override
            public List<BlogVO> execute() {
                List<BlogVO> blogVOList = blogHomeDao.getArticleListOfMyself(userId, Constants.ARTICLE_TYPE_BLOG);

                for (BlogVO blogVO: blogVOList) {
                    blogVO.setTagList(blogHomeDao.getTagListOfArticle(blogVO.getArticleid()));
                }

                return blogVOList;
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(pageParam, query);
    }

    /**
     * 获取推荐的的博客列表
     * @param blogDto 分页参数
     * @param userId    用户 ID
     * @return 推荐的的博客列表
     */
    @Override
    public Map<String, Object> getRecommendBlogList(BlogDto blogDto, Integer userId) {
        class queryAction implements  QueryAction<BlogVO> {
            @Override
            public List<BlogVO> execute() {
                return blogHomeDao.getArticleListOfRecommend(userId, blogDto.getCategoryId(), Constants.ARTICLE_TYPE_BLOG);
            }
        }

        queryAction query = new queryAction();
        return Utils.getPage(blogDto, query);
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
