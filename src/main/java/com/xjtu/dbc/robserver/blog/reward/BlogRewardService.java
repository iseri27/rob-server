package com.xjtu.dbc.robserver.blog.reward;

public interface BlogRewardService {
    void insertHistory(RewardDto dto);

    int getAuthoridByArticleid(Integer articleid);

    int getRewardHistory(RewardDto dto);
}
