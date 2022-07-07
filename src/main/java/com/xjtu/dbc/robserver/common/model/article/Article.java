package com.xjtu.dbc.robserver.common.model.article;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class Article {
    private Integer articleid;
    private Integer articletype;
    private Integer articlestatus;//是否发布。草稿 400；已发布但待审核 401；已发布并可见402；被隐藏 403
    private String title;
    private String content;
    private Integer cost;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastmodifytime;
    private Integer replyto;
    private Integer authorid;
    private Integer category;
}
