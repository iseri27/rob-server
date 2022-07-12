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

    @Override
    public Integer getMaxReportId() {
        return questionReportDao.getMaxReportId();
    }

    @Override
    public void addReport(QuestionReportDto questionReportDto) {
        questionReportDao.addReport(questionReportDto);
    }
}
