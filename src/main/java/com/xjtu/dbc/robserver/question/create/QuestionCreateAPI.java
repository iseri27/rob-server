package com.xjtu.dbc.robserver.question.create;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.question.create.entity.QuestionCreateDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
        QuestionCreateDto tempDto = questionCreateService.findQuestionById(questionCreateDto.getQuestionid());
        if(tempDto != null && tempDto.getQuestionstatus() == 400 ){
            questionCreateDto.setQuestionstatus(401);
            questionCreateService.modifyQuestion(questionCreateDto);
        }
        else {
            questionCreateDto.setQuestionstatus(401);
            questionCreateService.createQuestion(questionCreateDto);
        }
        return Result.success("问题创建成功，等待审核!", questionCreateDto.getQuestionid());
    }

    /**
     * 保存问题至草稿箱.
     * @param questionCreateDto,token
     * @return Result(msg, questionid)
     */
    @PostMapping("/save")
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
    @PostMapping("/delete")
    public Result deleteQuestion(@RequestBody QuestionCreateDto questionCreateDto,@RequestHeader("Token") String token){

        questionCreateService.deleteQuestionById(questionCreateDto.getQuestionid());

        return Result.success("成功删除草稿!", questionCreateDto.getQuestionid());
    }

    /**
     * 修改草稿箱问题.
     * @param questionCreateDto,token
     * @return Result(msg, questionid)
     */
    @PostMapping("/modify")
    public Result modifyQuestion(@RequestBody QuestionCreateDto questionCreateDto,@RequestHeader("Token") String token){

        //questionCreateDto.setQuestionid(1);
        questionCreateDto.setQuestionstatus(400);
        questionCreateService.modifyQuestion(questionCreateDto);

        return Result.success("草稿保存成功!", questionCreateDto.getQuestionid());
    }
}
