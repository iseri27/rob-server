package com.xjtu.dbc.robserver.level;

public interface LevelService {
    int getExp(int userID);

    void addExp(int userID, int num);

    int getCans(int userID);

    void updateCans(int userID, int num);
}
