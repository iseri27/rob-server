package com.xjtu.dbc.robserver.blog.home;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedOutput;
import com.xjtu.dbc.robserver.blog.home.entity.BlogDto;
import com.xjtu.dbc.robserver.blog.home.entity.BlogVO;
import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.model.article.Article;
import com.xjtu.dbc.robserver.common.model.category.Category;
import com.xjtu.dbc.robserver.common.model.user.User;
import com.xjtu.dbc.robserver.common.page.PageParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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

    @GetMapping("/rss")
    public String getRssFeed(@RequestParam("userId") Integer userId) {
        User user = commonService.getUserById(userId);
        String userName = user.getUsername();
        List<BlogVO> blogList = blogHomeService.getRssBlogList(userId);

        Channel channel = new Channel("rss_2.0");
        channel.setTitle("ROB Blog -- " + userName);
        channel.setLink("http://localhost:3000");
        channel.setDescription("红橙蓝博客系统");
        channel.setEncoding("utf-8");
        channel.setLanguage("zh_cn");
        // 最后生成时间
        channel.setLastBuildDate(new Date());
        // 在刷新当前 rss 在缓存中可以保存多长时间（分钟）
        channel.setTtl(5);
        // 设置生成时间
        channel.setPubDate(new Date());

        List<Item> itemList = new ArrayList<>();

        for (BlogVO blog: blogList) {
            Item item = new Item();
            item.setAuthor(userName);
            item.setTitle(blog.getTitle());
            item.setPubDate(blog.getCreatetime());

            // 设置分类
            List<com.rometools.rome.feed.rss.Category> categoryList = new ArrayList<>();
            com.rometools.rome.feed.rss.Category category = new com.rometools.rome.feed.rss.Category();
            category.setValue(blog.getCategoryname());
            item.setCategories(categoryList);

            // 设置内容
            Content content = new Content();
            content.setType(blog.getContent());
            item.setContent(content);

            itemList.add(item);
        }

        channel.setItems(itemList);

        WireFeedOutput out = new WireFeedOutput();
        String xml = null;
        try {
            xml = out.outputString(channel);
        } catch (FeedException e) {
            e.printStackTrace();
        }

        return xml;

//        return Result.successData(xml);
    }

}
