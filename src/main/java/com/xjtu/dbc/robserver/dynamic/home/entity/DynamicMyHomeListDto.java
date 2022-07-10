package com.xjtu.dbc.robserver.dynamic.home.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DynamicMyHomeListDto {

    private Integer articleid;
    private Integer articletype;
    private Integer articlestatus;
    private String content;
    private Integer authorid;
    private Integer like_num;
    private Integer dislike_num;
    private Integer comment_num;
    private Integer vote_type;  //0:未投票  800:赞  801:踩


    private Integer is_search_visible; //用于表示是否搜索栏可见   初始为1  1:可见 2：不可见
//    pattern = "yyyy-MM-dd HH:mm:ss",

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /*
    * 自己主页看到的自己的关注的人
    * */

    private Integer userid;
    private String username;
    private String usersex;
    private String useravatar;
    private String useremail;
    private String userabout;
    private Integer roleid;
    private Integer userstatus;


    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getArticletype() {
        return articletype;
    }

    public void setArticletype(Integer articletype) {
        this.articletype = articletype;
    }

    public Integer getArticlestatus() {
        return articlestatus;
    }

    public void setArticlestatus(Integer articlestatus) {
        this.articlestatus = articlestatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getLike_num() {
        return like_num;
    }

    public void setLike_num(Integer like_num) {
        this.like_num = like_num;
    }

    public Integer getDislike_num() {
        return dislike_num;
    }

    public void setDislike_num(Integer dislike_num) {
        this.dislike_num = dislike_num;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    public String getUserabout() {
        return userabout;
    }

    public void setUserabout(String userabout) {
        this.userabout = userabout;
    }

    public Integer getIs_search_visible() {
        return is_search_visible;
    }

    public void setIs_search_visible(Integer is_search_visible) {
        this.is_search_visible = is_search_visible;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getVote_type() {
        return vote_type;
    }

    public void setVote_type(Integer vote_type) {
        this.vote_type = vote_type;
    }

}
