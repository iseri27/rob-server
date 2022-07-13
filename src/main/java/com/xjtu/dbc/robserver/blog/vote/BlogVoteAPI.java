package com.xjtu.dbc.robserver.blog.vote;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/blog/vote")
public class BlogVoteAPI {
    @Resource
    private BlogVoteService voteService;


    @Resource
    private CommonService commonService;

    @PostMapping("/like")
    public Result ClickLike(@RequestBody VoteDto dto, @RequestHeader("Token") String token){
        Integer myid = TokenUtils.getUserInfo(token, commonService).getUserid();
        dto.setUserid(myid);
        int data = voteService.getVotesIfClickLike(dto);
        return Result.success("点赞成功！",data);
    }

    @PostMapping("/dislike")
    public Result ClickDislike(@RequestBody VoteDto dto, @RequestHeader("Token") String token){
        Integer myid = TokenUtils.getUserInfo(token, commonService).getUserid();
        dto.setUserid(myid);
        int data2 = voteService.getVotesIfClickDislike(dto);
        return Result.success("点踩成功！",data2);
    }

    @GetMapping("/getVotes")
    public Result GetVotes(VoteDto dto,@RequestHeader("Token") String token) {
        Integer myid = TokenUtils.getUserInfo(token, commonService).getUserid();
        dto.setUserid(myid);
        int[] data3 = voteService.getVotes(dto);
        return Result.successData(data3);
    }
}
