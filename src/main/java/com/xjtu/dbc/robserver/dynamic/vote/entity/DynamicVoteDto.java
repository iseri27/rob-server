package com.xjtu.dbc.robserver.dynamic.vote.entity;



public class DynamicVoteDto {
    private Integer voteid;
    private Integer votetype;
    private Integer userid;
    private Integer articleid;
    private Integer type;   //接收到的前端的赞踩情况

    public Integer getVoteid() {
        return voteid;
    }

    public void setVoteid(Integer voteid) {
        this.voteid = voteid;
    }

    public Integer getVotetype() {
        return votetype;
    }

    public void setVotetype(Integer votetype) {
        this.votetype = votetype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
