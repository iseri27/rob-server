package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.blog.home.entity.BlogDto;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.page.PageParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog/home")
public class BlogHomeAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private BlogHomeService blogHomeService;

    /**
     * 获取用户自己关注的人的的博客
     * @param pageParam 分页参数
     * @param token 令牌
     * @return 关注的人的博客
     */
    @GetMapping("/concerned")
    public Result getFollowersBlog(PageParam pageParam, @RequestHeader("Token") String token) {
        if (!commonService.isLogin(token)) {
            return Result.fail(Result.ERR_CODE_UNLOGIN, "用户未登录");
        }

        Map<String, Object> blogListPage = null;

        // 获取用户及其关注的用户的博客, 按发布时间排序
        Integer userId = null;
        try {
            userId = TokenUtils.getUserInfo(token, commonService).getUserid();
            blogListPage = blogHomeService.getBlogListOfConcernedUser(pageParam, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.successData(blogListPage);
    }

    /**
     * 获取我的博客
     * @param pageParam 分页参数
     * @param token 令牌
     * @return 我的博客列表
     */
    @GetMapping("/my")
    public Result getMyBlog(PageParam pageParam, @RequestHeader("Token") String token) {
        if (!commonService.isLogin(token)) {
            return Result.fail(Result.ERR_CODE_UNLOGIN, "用户未登录.");
        }

        Integer userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        Map<String, Object> blogListPage = blogHomeService.getMyBlogList(pageParam, userId);

        return Result.successData(blogListPage);
    }

    /**
     * 获取推荐的博客
     * @param blogDto 查询参数
     * @return 推荐的博客
     */
    @GetMapping("/recommend")
    public Result getRecommend(BlogDto blogDto, @RequestHeader("Token") String token) {
        Integer userId = null;
        if (commonService.isLogin(token)) {
            userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        }

        Map<String, Object> blogListPage = blogHomeService.getRecommendBlogList(blogDto, userId);
        return Result.successData(blogListPage);
    }

    /**获取分类列表
     * @return 分类列表
     */
    @GetMapping("/getCategories")
    public Result getCategories() {
        List<Category> categoryList = blogHomeService.getCategoryList();
        return Result.successData(categoryList);
    }
    public static int HISTORY_BROWSE = 701;

}
