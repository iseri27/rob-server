package com.xjtu.dbc.robserver.question.create.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
public class QuestionCreateDto {
    /**
     *  questionid:悬赏ID
     *  questiontitle:悬赏标题
     *  questioncontent:悬赏内容
     *  cost:悬赏的易拉罐数量
     *  createtime:创建时间
     *  lastmodifytime:最后更新时间
     *  authorid:作者ID
     *  questionstatus:问题状态
     *  type:2：问题
     */
    private int questionid;
    private String questiontitle;
    private String questioncontent;
    private int cost;
    private int authorid;
    private int questionstatus;
    private int type;
    private int categoryid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastmodifytime;

}
