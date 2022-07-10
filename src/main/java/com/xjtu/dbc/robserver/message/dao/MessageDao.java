package com.xjtu.dbc.robserver.message.dao;

import com.xjtu.dbc.robserver.message.entity.ChatFriend;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:29
 */
public interface MessageDao {
    List<ChatFriend> getChatFriendList(int userId);
    void startChat(int myId,int friendId);
}
