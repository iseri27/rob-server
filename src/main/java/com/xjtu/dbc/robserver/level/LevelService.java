package com.xjtu.dbc.robserver.level;

import com.xjtu.dbc.robserver.blog.reward.RewardDto;

public interface LevelService {
    int getExp(int userID);

    void addExp(int userID, int num);

    int getCans(int userID);

    void updateCans(int userID, int num);
}
