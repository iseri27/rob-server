package com.xjtu.dbc.robserver.blog.reply;

import com.xjtu.dbc.robserver.common.model.reply.Reply;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ReplyDto extends Reply {
    private String username; //评论者的姓名
    private String useravatar; //评论者的头像
}
