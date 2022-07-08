package com.xjtu.dbc.robserver.blog.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ReportDto {
    private Integer reportid;
    private Integer  userid;
    private Integer articleid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submittime;
    private String reason;
    private Integer reportstatus;
}
