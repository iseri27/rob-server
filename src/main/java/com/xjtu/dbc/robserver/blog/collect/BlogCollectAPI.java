package com.xjtu.dbc.robserver.blog.collect;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.common.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogCollectAPI {
    @Resource
    private BlogCollectService blogCollectService;

    @Resource
    private CommonService commonService;

    /**
     * 收藏博客
     */
    @PostMapping("/collect")
    public Result Collect(@RequestBody BlogCollect collect, @RequestHeader("Token") String token){
        BlogCollectDto dto = new BlogCollectDto();
        dto.setArticleid(collect.getArticleid());
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setOwnerid(myid);
        int bookmarkid = blogCollectService.getBookmarkid(dto);
        dto.setBookmarkid(bookmarkid);
        Integer res = blogCollectService.addCollect(dto);
        return Result.successData(res);
    }

    @PostMapping("/decollect")
    public Result Decollect(@RequestBody BlogCollect collect, @RequestHeader("Token") String token){
        BlogCollectDto dto = new BlogCollectDto();
        dto.setArticleid(collect.getArticleid());
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setOwnerid(myid);
        int bookmarkid = blogCollectService.getBookmarkid(dto);
        dto.setBookmarkid(bookmarkid);
        blogCollectService.deCollect(dto);
        return Result.successMsg("取消收藏成功！");
    }

    @GetMapping("/getCollect")
    public Result GetCollect(@RequestParam("articleid") Integer articleid, @RequestHeader("Token") String token) {
        BlogCollectDto dto = new BlogCollectDto();
        dto.setArticleid(articleid);
        int myid = TokenUtils.getUserInfo(token,commonService).getUserid();//当前用户id;
        dto.setOwnerid(myid);
        int bookmarkid = blogCollectService.getBookmarkid(dto);
        dto.setBookmarkid(bookmarkid);
        int ifCollectedOrNot = blogCollectService.getCollect(dto);
        return Result.successData(ifCollectedOrNot);
    }
}
