package com.xjtu.dbc.robserver.question.create;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagDto;
import com.xjtu.dbc.robserver.question.create.entity.QuestionTagListDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionCreateAPI {
    @Resource
    private  QuestionCreateService questionCreateService;

    @Resource
    private CommonService commonService;

    /**
     * 提交问题.
     * @param questionCreateDto,token
     * @return Result(msg, questionid)
     */
    @PostMapping("/create")//
    public Result createQuestion(@RequestBody QuestionCreateDto questionCreateDto,@RequestHeader("Token") String token){
        int authorstatus = commonService.getUserById(questionCreateDto.getAuthorid()).getUserstatus();
        QuestionCreateDto tempDto = questionCreateService.findQuestionById(questionCreateDto.getQuestionid());
        if (authorstatus == 200){
            if(tempDto != null && tempDto.getQuestionstatus() == 400 ){
                questionCreateDto.setQuestionstatus(402);
                questionCreateService.modifyQuestion(questionCreateDto);
            }
            else {
                questionCreateDto.setQuestionstatus(402);
                questionCreateService.createQuestion(questionCreateDto);
            }
            return Result.success("问题创建成功，等待审核!", questionCreateDto.getQuestionid());
        }
        else if(authorstatus == 201){

            return Result.fail(Result.ERR_CODE_BUSINESS,"你正在被禁言，无法发布悬赏！");
        }
        else{
            return Result.fail(Result.ERR_CODE_BUSINESS,"你已被封禁！");
        }
    }

    /**
     * 获取草稿对象
     */
    @GetMapping("/draft/get")
    public Result getDraft(@RequestParam("qid") int qid){
        try{
            int questionid = qid;
            QuestionCreateDto dto = questionCreateService.findQuestionById(qid);
            return Result.success("获取草稿成功！",dto);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(Result.ERR_CODE_BUSINESS, "获取草稿失败！");
        }

    }

    /**
     * 保存问题至草稿箱.
     * @param questionCreateDto,token
     * @return Result(msg, questionid)
     */
    @PostMapping("/draft/save")
    public Result saveQuestion(@RequestBody QuestionCreateDto questionCreateDto,@RequestHeader("Token") String token){

        questionCreateDto.setQuestionstatus(400);
        questionCreateService.saveQuestion(questionCreateDto);

        return Result.success("成功保存至草稿箱!", questionCreateDto.getQuestionid());
    }

    /**
     * 删除草稿.
     * @param questionCreateDto,token
     * @return Result(msg, questionid)
     */
    @PostMapping("/draft/delete")
    public Result deleteQuestion(@RequestBody QuestionCreateDto questionCreateDto,@RequestHeader("Token") String token){

        questionCreateService.deleteQuestionById(questionCreateDto.getQuestionid());

        return Result.success("成功删除草稿!", questionCreateDto.getQuestionid());
    }

    /**
     * 修改草稿箱问题.
     * @param questionCreateDto,token
     * @return Result(msg, questionid)
     */
    @PostMapping("/draft/modify")
    public Result modifyQuestion(@RequestBody QuestionCreateDto questionCreateDto,@RequestHeader("Token") String token){

        //questionCreateDto.setQuestionid(1);
        questionCreateDto.setQuestionstatus(400);
        questionCreateService.modifyQuestion(questionCreateDto);

        return Result.success("草稿修改成功!", questionCreateDto.getQuestionid());
    }

    @PostMapping("/tag/add")
    public Result addTag(@RequestBody Tag tag, @RequestHeader("Token") String token){
        int count = questionCreateService.selectTagNum(tag.getOwnerid());

        if(count <= 20){
            commonService.addTag(tag);
            return Result.success("添加tag成功!", tag.getTagid());
        }else{
            return Result.fail(Result.ERR_CODE_BUSINESS, "最多可以创建20个标签!");
        }
    }
    @PostMapping("/tag/delete")
    public Result deleteTag(@RequestBody int tagid, @RequestHeader("Token") String token){
            commonService.deleteTag(tagid);
            return Result.success("删除tag成功!", tagid);

    }

    @PostMapping("/tag/connect")
    public Result connectTag(@RequestBody QuestionTagDto questionTagDto, @RequestHeader("Token") String token){
        if(questionCreateService.getQuestionTagNum(questionTagDto.getQuestionid()) <= 3){

            questionCreateService.connectTag(questionTagDto);
            return Result.success("添加tag成功!", questionTagDto.getTagid());
        }
        else{
            return Result.fail(Result.ERR_CODE_BUSINESS, "悬赏最多可添加3个标签!");
        }
    }

    @PostMapping("/tag/disconnect")
    public Result disconnectTag(@RequestBody QuestionTagDto questionTagDto, @RequestHeader("Token") String token){
        questionCreateService.disconnectTag(questionTagDto);
        return Result.success("取消tag成功!", questionTagDto.getTagid());
    }

    @GetMapping("/tag/questiontag")
    public Result qTagList(int qid){
        int questionid = qid;
        List<QuestionTagListDto> listDto = questionCreateService.getQuestionTagListById(questionid);
        return Result.success("查询悬赏tag列表成功!", qid);
    }
}
