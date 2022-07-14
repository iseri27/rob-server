package com.xjtu.dbc.robserver.question.answer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
public class QuestionAnswerListDto {
    private Integer answerid;
    private Integer type;//博客 300；动态 301；悬赏 302；评论 303;回答304 ； 优质回答 305
    private Integer answerstatus;//草稿 400；已发布 401；被隐藏 402
    private String answertitle;
    private Integer authorid;
    private String authorname;
    private String authoravatar;
    private Integer like_num;
    private Integer dislike_num;
    private Integer comment_num;
    private Integer vote_type;


    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date lastmodifytime;
}
