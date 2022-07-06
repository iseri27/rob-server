package com.xjtu.dbc.robserver.dynamic.report.dao;

import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;

public interface DynamicReportDao {


    /**
     * 新增举报
     * @param dynamicReportDto 举报的信息
     */
    void addReport(DynamicReportDto dynamicReportDto);

    /**
     * 获取当前最大的举报记录的编号
     * @return 最大举报记录的编号
     */
    Integer getMaxReportId();
}
