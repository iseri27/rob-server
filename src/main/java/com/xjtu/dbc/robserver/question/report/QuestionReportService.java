package com.xjtu.dbc.robserver.question.report;

import com.xjtu.dbc.robserver.question.report.entity.QuestionReportDto;

public interface QuestionReportService {
    Integer getMaxReportId();

    void addReport(QuestionReportDto questionReportDto);
}
