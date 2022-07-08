package com.xjtu.dbc.robserver.blog.report.impl;

import com.xjtu.dbc.robserver.blog.report.BlogReportService;
import com.xjtu.dbc.robserver.blog.report.ReportDto;
import com.xjtu.dbc.robserver.blog.report.dao.BlogReportDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class BlogReportServiceImpl implements BlogReportService {
    @Resource
    private BlogReportDao blogReportDao;

    @Override
    public void report(ReportDto dto){
        blogReportDao.insertReport(dto);
    }
}
