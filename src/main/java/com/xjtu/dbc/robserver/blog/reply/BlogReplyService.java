package com.xjtu.dbc.robserver.blog.reply;

import com.xjtu.dbc.robserver.common.model.reply.Reply;

import java.util.List;

public interface BlogReplyService {
    void reply(Reply dto);

    List<ReplyDto> getReplyList(int articleid);

    Integer ifPullBlack(Integer myid, Integer replyto);

    boolean cannotDelReply(Reply dto);

    void delReply(Integer replyid);
}
