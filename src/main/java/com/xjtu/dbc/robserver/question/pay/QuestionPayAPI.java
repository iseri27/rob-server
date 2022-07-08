package com.xjtu.dbc.robserver.question.pay;

import com.xjtu.dbc.robserver.common.CommonService;
import com.xjtu.dbc.robserver.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question/pay")
public class QuestionPayAPI {

    @Resource
    private CommonService commonService;

    @Resource
    private QuestionPayService questionPayService;

    @PostMapping
    public Result payQuestion(@RequestHeader("Token") String token){

    return Result.success("1",1);
    }
}
