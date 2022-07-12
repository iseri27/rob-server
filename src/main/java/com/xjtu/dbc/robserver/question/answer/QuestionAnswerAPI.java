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
    public Result getAList(@RequestParam("qid") int qid,@RequestParam("selectid") int selectid){
        try{
            int questionid = qid;
            int sid = selectid;
            if(sid == 1){
                List<QuestionAnswerListDto> listDto = questionAnswerService.getAnswerList(questionid);
                return Result.success("获取回答列表成功",listDto);
            }
            else{
                List<QuestionAnswerListDto> listDto = questionAnswerService.getGoodAnswerList(questionid);
                return Result.success("获取优质回答列表成功",listDto);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "未找到匹配回答！");
        }
    }

    @GetMapping("/detail")
    public Result getAnswerDetails(@RequestParam("aid") int aid) {
        try{
            int answerid = aid;
            AnswerDetailsDto answerDetailsDto= questionAnswerService.getAnswerDetails(answerid);
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
