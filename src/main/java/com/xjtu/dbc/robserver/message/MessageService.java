package com.xjtu.dbc.robserver.message;

import com.xjtu.dbc.robserver.message.entity.ChatFriend;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:11
 */
public interface MessageService {
    List<ChatFriend> getChatFriends(int userId);
    void startChat(int myId,int friendId);
}
