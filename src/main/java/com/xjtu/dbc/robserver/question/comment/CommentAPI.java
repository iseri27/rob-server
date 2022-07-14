package com.xjtu.dbc.robserver.question.comment;


import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.question.answer.QuestionAnswerService;
import com.xjtu.dbc.robserver.question.comment.entity.CommentDto;
import com.xjtu.dbc.robserver.question.home.QuestionHomeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question/comment")
public class CommentAPI {

    @Resource
    private CommentService commentService;

    @Resource
    private QuestionHomeService questionHomeService;

    @Resource
    private QuestionAnswerService questionAnswerService;

    /**
     * 获取评论列表
     * @param num
     * @return Result(msg, listDto)
     */
    @GetMapping("")
    public Result getCommnent(@RequestParam("num") Integer num) {
        Integer articleid = num;
        System.out.println("测试articleid: "+articleid);
        List<CommentDto> listDto = commentService.getCommentList(articleid);


        for(int i=0; i<listDto.size();i++){
            listDto.get(i).setLike_num(questionHomeService.getLikenumByQuestionId(listDto.get(i).getArticleid()));       //获取每条评论的点赞数
            listDto.get(i).setDislike_num(questionHomeService.getDislikenumByQuestionId(listDto.get(i).getArticleid()));        //获取每条评论的点踩数
            listDto.get(i).setVote_type(questionAnswerService.getVoteTypeByU_A_id(listDto.get(i).getUserid(),listDto.get(i).getArticleid())); // vote_type表示用户赞踩的情况 其中 vote_type的值为 null:未投票  800:赞  801:踩
        }
        //还需要得到用户的粉丝数 动态数
        return Result.success("获取动态的评论列表成功!", listDto);
    }





    /**
     * 发布用户评论
     * @param commentDto
     * @return Result(msg, dynamicCommentDto)
     */
    @PostMapping("publishcomment")
    public Result publishComment(@RequestBody CommentDto commentDto) {

        Integer maxId = commentService.getMaxCommentId();
        commentDto.setArticleid(maxId + 1);
        commentDto.setArticletype(303);  //评论所对应的文本类型为303
        commentDto.setArticlestatus(402);  //评论的状态设置为402

        commentService.addComment(commentDto);
        return Result.success("发布评论成功!", commentDto);
    }

}
