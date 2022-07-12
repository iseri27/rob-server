package com.xjtu.dbc.robserver.question.report.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
public class QuestionReportDto {
    private Integer reportid;
    private String reason;
    private Integer articleid;
    private Integer userid;
    private Integer reportstatus;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submittime;


}
