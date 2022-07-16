package com.xjtu.dbc.robserver.question.report;

import com.xjtu.dbc.robserver.question.report.entity.QuestionReportDto;

public interface QuestionReportService {

    /**
     * 获取当前最大的举报记录的编号
     * @return 最大举报记录的编号
     */
    Integer getMaxReportId();

    /**
     * 新增举报
     * @param questionReportDto 举报的信息
     */
    void addReport(QuestionReportDto questionReportDto);
}
