package com.xjtu.dbc.robserver.blog.vote;

public interface BlogVoteService {

    int getVotesIfClickLike(VoteDto dto);

    int getVotesIfClickDislike(VoteDto dto);

    int[] getVotes(VoteDto dto);
}
