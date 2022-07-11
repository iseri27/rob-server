package com.xjtu.dbc.robserver.blog.detail;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.level.Level;
import com.xjtu.dbc.robserver.level.LevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog")
public class BlogDetailAPI {
    @Resource
    private BlogDetailService blogDetailService;

    @Resource
    private LevelService levelService;

    @Resource
    private CommonService commonService;
    /**
     * 获取博客详情
     */
    @GetMapping("/detail")
    public Result getEssayDetail(@RequestParam("articleid") int articleid, @RequestHeader("Token") String token) {
        try {
            BlogDetailDto blog = blogDetailService.getBlogDetailByArticleid(articleid);

            if (blog.getArticlestatus()==400) {
                // 该随笔未发布，需要验证登录状态，未登录状态下或并非作者都不能查看
                try {
                    TokenUtils.verifyToken(token, commonService);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.fail(Result.ERR_CODE_BUSINESS, "博客未发布，请登录！");
                }

                // 虽然该用户登录了，但并非是这篇随笔的作者
                int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
                if (myid!=blog.getAuthorid()) {
                    return Result.fail(Result.ERR_CODE_BUSINESS, "不能编辑不属于自己的博客！");
                }
            }
            int value = levelService.getLevel(blog.getAuthorid());
            Level level = new Level(value);
            blog.setLevelname(level.getName());
            return Result.successData(blog);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_SYS, "系统错误！获取博客信息失败！");
        }
    }

    /**
     * 获取当前用户id
     */
    @GetMapping("/currentUser")
    public Result getCurrentUser(@RequestHeader("Token") String token) {
        Integer myid = TokenUtils.getUserInfo(token,commonService).getUserid();
        CurrentUserDto currentUser = blogDetailService.getCurrentUser(myid);
        return Result.success("获取用户信息成功",currentUser);
    }
}
