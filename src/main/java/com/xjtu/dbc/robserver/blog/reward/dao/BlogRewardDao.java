package com.xjtu.dbc.robserver.blog.reward.dao;

import com.xjtu.dbc.robserver.blog.reward.RewardDto;

public interface BlogRewardDao {
    void insertHistory(RewardDto dto);

    int getAuthoridByArticleid(Integer articleid);

    int getRewrdHistory(RewardDto dto);
}
