package com.xjtu.dbc.robserver.question.report;

import com.xjtu.dbc.robserver.common.Result;
import com.xjtu.dbc.robserver.question.report.entity.QuestionReportDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question/report")
public class QuestionReportAPI {

    @Resource
    QuestionReportService questionReportService;

    @PostMapping("")
    public Result report(@RequestBody QuestionReportDto questionReportDto) {

        Integer maxId = questionReportService.getMaxReportId();
        questionReportDto.setReportid(maxId + 1);
        questionReportDto.setReportstatus(500);  //举报状态初始设置为500

        questionReportService.addReport(questionReportDto);
        return Result.success("提交举报成功!", questionReportDto);
    }
}
