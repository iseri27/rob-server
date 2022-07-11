package com.xjtu.dbc.robserver.question.answer;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question/answer")
public class QuestionAnswerAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private QuestionAnswerService questionAnswerService;

    @GetMapping("/alist")
    public Result getAList(@RequestParam("qid") int qid){
        try{
            int questionid = qid;
            List<QuestionAnswerListDto> listDto = questionAnswerService.getAnswerList(questionid);
            for(int i=0;i < listDto.size();i++){
                listDto.get(i).setLike_num(questionAnswerService.getLikenumByAnswerId(listDto.get(i).getAnswerid()));
                listDto.get(i).setDislike_num(questionAnswerService.getDislikenumByAnswerId(listDto.get(i).getAnswerid()));
                listDto.get(i).setComment_num(questionAnswerService.getCommentNum(listDto.get(i).getAnswerid()));
            }
            return Result.success("获取回答列表成功",listDto);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "未找到匹配回答！");
        }
    }

    @GetMapping("/detail")
    public Result getQuestionDetails(@RequestParam("qid") int qid) {
        try{
            int questionid = qid;
            AnswerDetailsDto answerDetailsDto= questionAnswerService.getAnswerDetails(questionid);
            answerDetailsDto.setLike_num(questionAnswerService.getLikenumByAnswerId(questionid));
            answerDetailsDto.setDislike_num(questionAnswerService.getDislikenumByAnswerId(questionid));
            answerDetailsDto.setComment_num(questionAnswerService.getCommentNum(questionid));
            return Result.success("查找回答详情成功",answerDetailsDto);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "查找回答详情失败！");
        }
    }

    @PostMapping("/create")
    public Result createAnswer(@RequestBody AnswerDto answerDto, @RequestHeader("Token") String token){
        try{
            questionAnswerService.createAnswer(answerDto);

            return Result.success("创建回答成功，等待审核！",answerDto);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "创建回答失败！");
        }
    }


}
