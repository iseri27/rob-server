package com.xjtu.dbc.robserver.question.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class CommentDto {
    private Integer articleid;
    private Integer articletype;
    private Integer articlestatus;
    private String content;
    private Integer authorid;
    private Integer like_num;
    private Integer dislike_num;
    private Integer replyto;
    private Integer rootid;
    private Integer vote_type;  //0:未投票  800:赞  801:踩

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastmodifytime;

    private Integer userid;
    private String username;
    private String usersex;
    private String useravatar;
    private String useremail;
    private String userabout;
    private Integer roleid;
    private Integer userstatus;
}
