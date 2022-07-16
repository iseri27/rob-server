package com.xjtu.dbc.robserver.question.report.impl;


import com.xjtu.dbc.robserver.question.report.QuestionReportService;
import com.xjtu.dbc.robserver.question.report.dao.QuestionReportDao;
import com.xjtu.dbc.robserver.question.report.entity.QuestionReportDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class QuestionReportServiceImpl implements QuestionReportService {

    @Resource
    private QuestionReportDao questionReportDao;

    /**
     * 获取当前最大的举报记录的编号
     * @return 最大举报记录的编号
     */
    @Override
    public Integer getMaxReportId() {
        return questionReportDao.getMaxReportId();
    }

    /**
     * 新增举报
     * @param questionReportDto 举报的信息
     */
    @Override
    public void addReport(QuestionReportDto questionReportDto) {
        questionReportDao.addReport(questionReportDto);
    }
}
