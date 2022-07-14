package com.xjtu.dbc.robserver.blog.reply.dao;

import com.xjtu.dbc.robserver.blog.reply.ReplyDto;
import com.xjtu.dbc.robserver.common.model.reply.Reply;

import java.util.List;

public interface BlogReplyDao {
    void insertReply(Reply dto);

    List<ReplyDto> getReplyList(int articleid);

    Integer ifPullBlack(Integer myid, Integer replyto);

    Integer isMyArticle(Integer replyto, Integer myid);

    void delReply(Integer replyid);

    Integer getReplytouserid(Integer replyto);
}
