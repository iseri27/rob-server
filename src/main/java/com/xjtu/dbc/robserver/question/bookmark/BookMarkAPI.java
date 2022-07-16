package com.xjtu.dbc.robserver.question.bookmark;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;

import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.question.bookmark.entity.BookmarkDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question/bookmark")
public class BookMarkAPI {
    @Resource
    BookmarkService bookmarkService;

    @Resource
    CommonService commonService;

    /**
     * 取消(添加)收藏该文章
     * @param bookmarkDto{articleid 文章id ，userid 用户id}
     */
    @PostMapping("")
    public Result delBookmark(@RequestBody BookmarkDto bookmarkDto) {
        if(bookmarkService.ifBookmark(bookmarkDto)!=0){
            bookmarkService.deleteBookmark(bookmarkDto);
            return Result.success("取消收藏成功!", bookmarkDto);
        }
        else{
            bookmarkService.addBookmark(bookmarkDto);
            return Result.success("添加收藏成功!", bookmarkDto);
        }
    }

    /**
     * 当前用户是否收藏该文章
     * @param Articleid 文章id
     * @return 收藏关系
     */
    @GetMapping("/info")
    public Result ifBookmark(@RequestParam("Articleid") int Articleid,@RequestHeader("Token") String token){
        try{
            int Userid = TokenUtils.getUserInfo(token,commonService).getUserid();
            BookmarkDto dto = new BookmarkDto();
            dto.setUserid(Userid);
            dto.setArticleid(Articleid);
            if(bookmarkService.ifBookmark(dto) == 0){
                return Result.success("查询是否收藏成功！",false);
            }
            else{
                return Result.success("查询是否收藏成功！",true);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "查询失败！");
        }

    }
}
