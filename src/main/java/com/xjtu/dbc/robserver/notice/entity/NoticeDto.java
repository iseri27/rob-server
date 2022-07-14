package com.xjtu.dbc.robserver.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/14 10:35
 */
@Data
public class NoticeDto {
    Integer type;
    Integer articleid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date time;
}
