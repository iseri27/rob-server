package com.xjtu.dbc.robserver.question.pay;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.common.TokenUtils;
import com.xjtu.dbc.robserver.level.LevelService;
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

    @Resource
    private LevelService levelService;

    @PostMapping("")
    public Result payQuestion(@RequestBody PayDto dto, @RequestHeader("Token") String token){

        System.out.println(dto.getAnswerid());
        if(dto.getCost() > 0){
            System.out.println("恍恍惚惚");
            questionPayService.payQuestion(dto.getQuestionid());
            questionPayService.setGoodAnswer(dto.getAnswerid());
            levelService.updateCans(dto.getAuthorid(), dto.getCost());
            return Result.success("设置为优质回答成功,悬赏已完成",dto);
        }
        else{
            questionPayService.setGoodAnswer(dto.getAnswerid());
            return Result.success("设置为优质回答成功",dto);
        }

    }
}
