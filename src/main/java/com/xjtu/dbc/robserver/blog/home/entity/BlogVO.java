package com.xjtu.dbc.robserver.blog.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class BlogVO {
    private Integer articleid;
    private Integer authorid;
    private String authorname;
    private String authoravatar;
    private String title;
    private String content;
    private Integer categoryid;
    private String categoryname;
    private List<String> tagList;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastmodifytime;

}