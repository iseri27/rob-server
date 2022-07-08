package com.xjtu.dbc.robserver.blog.home.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class BlogVO {
    private Integer articleid;
    private Integer authorid;
    private String authorname;
    private String authoravatar;
    private String title;
    private String content;
    private Date createtime;
    private Date lastmodifytime;
    private Integer categoryid;
}