package com.xjtu.dbc.robserver.blog.vote.dao;

import com.xjtu.dbc.robserver.blog.vote.VoteDto;

public interface BlogVoteDao {
    void deleteVote(VoteDto dto);

    void updateVote(VoteDto dto);

    void insertLike(VoteDto dto);

    void insertDislike(VoteDto dto);

    int hadLikedOrNot(VoteDto dto);

    int hadDislikedOrNot(VoteDto dto);

    int findLikes(VoteDto dto);

    int findDislikes(VoteDto dto);
}
