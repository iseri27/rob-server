package com.xjtu.dbc.robserver.question.bookmark;

import com.xjtu.dbc.robserver.common.Result;

import com.xjtu.dbc.robserver.question.bookmark.entity.BookmarkDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question/bookmark")
public class BookMarkAPI {
    @Resource
    BookmarkService bookmarkService;

    @PostMapping("")
    public Result report(@RequestBody BookmarkDto bookmarkDto) {
        if(bookmarkService.ifBookmark(bookmarkDto)!=0){
            bookmarkService.deleteBookmark(bookmarkDto);
            return Result.success("取消收藏成功!", bookmarkDto);
        }
        else{
            bookmarkService.addBookmark(bookmarkDto);
            return Result.success("添加收藏成功!", bookmarkDto);
        }
    }

    @GetMapping("/info")
    public Result ifBookmark(@RequestParam("Userid") int Userid,@RequestParam("Articleid") int Articleid){
        try{
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
