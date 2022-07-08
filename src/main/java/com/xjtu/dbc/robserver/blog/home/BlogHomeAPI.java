package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.page.PageParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/blog/home")
public class BlogHomeAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private BlogHomeService blogHomeService;

    @GetMapping("")
    public Result getHomeBlog(PageParam pageParam, @RequestHeader("Token") String token) {

        Map<String, Object> blogListPage = null;
        if (!commonService.isLogin(token)) {
            // 用户未登录, 根据算法选取一定的博客进行展示
            blogListPage = blogHomeService.getBlogList(pageParam,null, 50);
        }

        // 获取用户及其关注的用户的博客, 按发布时间排序
        Integer userId = null;
        try {
            userId = TokenUtils.getUserInfo(token, commonService).getUserid();
            blogListPage = blogHomeService.getBlogList(pageParam, userId, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.successData(blogListPage);
    }
}
