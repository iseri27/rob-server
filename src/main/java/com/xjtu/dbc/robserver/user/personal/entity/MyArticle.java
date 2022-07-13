package com.xjtu.dbc.robserver.user.personal.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyArticle{
    private Integer articleid;
    private String title;
    private String content;
    private Integer authorid;
    private String useravatar;
}
