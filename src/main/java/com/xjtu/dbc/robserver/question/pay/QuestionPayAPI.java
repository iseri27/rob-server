package com.xjtu.dbc.robserver.question.pay;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.question.pay.entity.PayDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question/pay")
public class QuestionPayAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private QuestionPayService questionPayService;

    @PostMapping
    public Result payQuestion(@RequestBody PayDto dto, @RequestHeader("Token") String token){

        if(dto.getCost() > 0){
            questionPayService.payQuestion(dto.getQuestionid());
            questionPayService.setGoodAnswer(dto.getAnswerid());
            return Result.success("设置为优质回答成功,悬赏已完成",dto);
        }
        else{
            questionPayService.setGoodAnswer(dto.getAnswerid());
            return Result.success("设置为优质回答成功",dto);
        }

    }
}
