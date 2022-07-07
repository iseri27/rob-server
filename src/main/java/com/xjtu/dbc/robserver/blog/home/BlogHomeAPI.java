package com.xjtu.dbc.robserver.blog.home;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog/home")
public class BlogHomeAPI {
    @Resource
    private CommonService commonService;
    @Resource
    private BlogHomeService blogHomeService;

    @GetMapping("")
    public Result getHomeBlogs(@RequestHeader("Token") String token) {

        if (!commonService.isLogin(token)) {
            // 用户未登录, 根据算法选取一定的博客进行展示
        }

        Integer userId = null;
        try {
            userId = TokenUtils.getUserInfo(token, commonService).getUserid();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.success();
    }
}
