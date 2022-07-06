package com.xjtu.dbc.robserver.dynamic.report;

import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.user.register.entity.RegisterDto;

public interface DynamicReportService {


    /**
     * 新增举报
     * @param dynamicReportDto {举报原因, 文本编号, 举报时间, 举报用户编号}
     */
    void addReport(DynamicReportDto dynamicReportDto);

    /**
     * 获取当前最大的举报记录的编号
     * @return 最大的记录的编号
     */
    Integer getMaxReportId();


}
