package com.xjtu.dbc.robserver.question.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class QuestionHomeListDto {
    private Integer questionid;
    private Integer type;//博客 300；动态 301；悬赏 302；评论 303
    private Integer questionstatus;//草稿 400；已发布 401；被隐藏 402
    private String questiontitle;
    private Integer authorid;
    private String authorname;
    private String authoravatar;
    private Integer like_num;
    private Integer dislike_num;
    private Integer comment_num;
    private List<String> taglist;
    private Integer categoryid;
    private int cost;


    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date lastmodifytime;

}
