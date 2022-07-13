package com.xjtu.dbc.robserver.blog.vote;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class VoteDto {
    Integer userid;
    Integer articleid;
    Integer votetype;//-1点踩，0没点赞也没点踩，1点赞
}
