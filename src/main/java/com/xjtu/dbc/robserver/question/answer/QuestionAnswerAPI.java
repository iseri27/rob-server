package com.xjtu.dbc.robserver.question.answer;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.page.PageParam;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDto;
import com.xjtu.dbc.robserver.question.answer.entity.AnswerDetailsDto;
import com.xjtu.dbc.robserver.question.answer.entity.QuestionAnswerListDto;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question/answer")
public class QuestionAnswerAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private QuestionAnswerService questionAnswerService;

    @GetMapping("/alist")
    public Result getAList(PageParam pageParam,@RequestParam("qid") int qid,@RequestParam("selectid") int selectid,@RequestParam("uid") int uid){
        try{
            int userid = uid;
            int questionid = qid;
            int sid = selectid;
            System.out.println("红红火火恍恍惚惚");
            if(sid == 1){

//                List<QuestionAnswerListDto> listDto = questionAnswerService.getAnswerList(questionid);
//                for(int i=0; i<listDto.size();i++){
//                    System.out.println(233333333);
//                    listDto.get(i).setVote_type(questionAnswerService.getVoteTypeByU_A_id(userid,listDto.get(i).getAnswerid()));
//                }
//                return Result.success("获取回答列表成功",listDto);
                Map<String, Object> answerListPage = questionAnswerService.getAnswerList(pageParam,questionid, userid);

                return Result.successData(answerListPage);
            }
            else{
//                List<QuestionAnswerListDto> listDto = questionAnswerService.getGoodAnswerList(questionid);
//                for(int i=0; i<listDto.size();i++) {
//                    listDto.get(i).setVote_type(questionAnswerService.getVoteTypeByU_A_id(userid, listDto.get(i).getAnswerid()));
//                }
//                return Result.success("获取优质回答列表成功",listDto);
                Map<String, Object> answerListPage = questionAnswerService.getGoodAnswerList(pageParam,questionid, userid);
                return Result.successData(answerListPage);

            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "未找到匹配回答！");
        }
    }

    @GetMapping("/detail")
    public Result getAnswerDetails(@RequestParam("aid") int aid,@RequestParam("uid") int uid) {
        try{
            int answerid = aid;
            int userid = uid;
            AnswerDetailsDto answerDetailsDto= questionAnswerService.getAnswerDetails(answerid);
            answerDetailsDto.setVote_type(questionAnswerService.getVoteTypeByU_A_id(userid,answerid));
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
