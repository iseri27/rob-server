package com.xjtu.dbc.robserver.dynamic.report.impl;

import com.xjtu.dbc.robserver.dynamic.report.DynamicReportService;
import com.xjtu.dbc.robserver.dynamic.report.dao.DynamicReportDao;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.user.register.dao.RegisterDao;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service @Transactional
public class DynamicReportServiceImpl implements DynamicReportService {

    @Resource
    private DynamicReportDao dynamicReportDao;




    /**
     * 新增举报
     * @param dynamicReportDto {举报原因, 文本编号, 举报时间, 举报用户编号}
     */
    @Override
    public  void addReport(DynamicReportDto dynamicReportDto) {
        dynamicReportDao.addReport(dynamicReportDto);
    }



    /**
     * 获取当前最大的举报记录的编号
     * @return 最大的记录的编号
     */
    @Override
    public Integer getMaxReportId() {
        return dynamicReportDao.getMaxReportId();
    }
}
