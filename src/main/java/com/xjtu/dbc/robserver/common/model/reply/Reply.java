package com.xjtu.dbc.robserver.common.model.reply;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Reply {
    private Integer replyid;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    private Integer replyto;//回复对象的帖子id
    private Integer replytouserid;//回复对象的用户id
    private Integer authorid;
    private Integer rootid;
}
