package com.xjtu.dbc.robserver.dynamic.comment;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.DynamicHomeService;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.report.DynamicReportService;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dynamic/comment")
public class DynamicCommentAPI {

    @Resource
    private DynamicCommentService dynamicCommentService;

    @Resource
    private DynamicHomeService dynamicHomeService;

    /**
     * 获取评论列表
     * @param num
     * @return Result(msg, listDto)
     */
    @GetMapping("")
    public Result getCommnent(@RequestParam("num") Integer num) {
        Integer articleid = num;
        System.out.println("测试articleid: "+articleid);
        List<DynamicCommentDto> listDto = dynamicCommentService.getDynamicCommentList(articleid);


        for(int i=0; i<listDto.size();i++){
            listDto.get(i).setLike_num(dynamicHomeService.getLikenumByAriticleid(listDto.get(i).getArticleid()));       //获取每条评论的点赞数
            listDto.get(i).setDislike_num(dynamicHomeService.getDislikenumByAriticleid(listDto.get(i).getArticleid()));        //获取每条评论的点踩数
            listDto.get(i).setVote_type(dynamicHomeService.getVoteTypeByU_A_id(listDto.get(i).getUserid(),listDto.get(i).getArticleid())); // vote_type表示用户赞踩的情况 其中 vote_type的值为 null:未投票  800:赞  801:踩
        }

        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态的评论列表成功!", listDto);
    }





    /**
     * 发布用户评论
     * @param dynamicCommentDto
     * @return Result(msg, dynamicCommentDto)
     */
    @PostMapping("publishcomment")
    public Result publishComment(@RequestBody DynamicCommentDto dynamicCommentDto) {

//        Integer userststus =  dynamicHomeService.getUserstatus(dynamicCommentDto.getAuthorid());
        DynamicHomeDto authorDto = dynamicHomeService.getUserInfo(dynamicCommentDto.getAuthorid());

        if(authorDto.getUserstatus()==201 || authorDto.getUserstatus()==202){  //如果当前用户被禁言或封禁
            return Result.fail(Result.ERR_CODE_BUSINESS, "当前用户状态不可发言！");
        }

        if(dynamicCommentService.is_in_blacklist(dynamicCommentDto.getAuthorid(),dynamicCommentDto.getReplyto())){
            return Result.fail(Result.ERR_CODE_BUSINESS, "已被该用户拉黑不可回复！");
        }

        Integer maxId = dynamicCommentService.getMaxCommentId();
        dynamicCommentDto.setArticleid(maxId + 1);
        dynamicCommentDto.setArticletype(303);  //评论所对应的文本类型为303
        dynamicCommentDto.setArticlestatus(402);  //评论的状态设置为402

        dynamicCommentService.addComment(dynamicCommentDto);
        return Result.success("发布评论成功!", dynamicCommentDto);
    }

}
