package com.xjtu.dbc.robserver.blog.collect;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController//本注解说明本类的对象将作为受spring容器管理的对象，并且说明这是一个控制器组件(Bean)
@RequestMapping("/blog")//本注解说明本控制器内的所有路径都以之开头
public class BlogCollectAPI {
    @Resource//从容器中引入依赖对象
    private BlogCollectService blogCollectService;

    @Resource
    private CommonService commonService;

    @PostMapping("/collect")
    public Result Collect(@RequestBody BlogCollectDto dto, @RequestHeader("Token") String token){
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setOwnerid(myid);
        int bookmarkid = blogCollectService.getBookmarkid(dto);
        dto.setBookmarkid(bookmarkid);
        blogCollectService.addCollect(dto);
        return Result.successMsg("收藏成功！");
    }

    @PostMapping("/decollect")
    public Result Decollect(@RequestBody BlogCollectDto dto, @RequestHeader("Token") String token){
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setOwnerid(myid);
        int bookmarkid = blogCollectService.getBookmarkid(dto);
        dto.setBookmarkid(bookmarkid);
        blogCollectService.deCollect(dto);
        return Result.successMsg("取消收藏成功！");
    }

    @GetMapping("/getCollect")
    public Result GetCollect(@RequestBody BlogCollectDto dto, @RequestHeader("Token") String token) {
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setOwnerid(myid);
        int bookmarkid = blogCollectService.getBookmarkid(dto);
        dto.setBookmarkid(bookmarkid);
        int ifCollectedOrNot = blogCollectService.getCollect(dto);
        return Result.successData(ifCollectedOrNot);
    }
}
