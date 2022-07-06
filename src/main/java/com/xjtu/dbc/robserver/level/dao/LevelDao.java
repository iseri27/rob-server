package com.xjtu.dbc.robserver.level.dao;

public interface LevelDao {
    int getExp(int userID);

    void updateExp(int userID, int num);

    int getCans(int userID);

    void updateCans(int userID, int num);
}
