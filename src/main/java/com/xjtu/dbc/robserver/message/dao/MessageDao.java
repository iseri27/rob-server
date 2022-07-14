package com.xjtu.dbc.robserver.message.dao;

import com.xjtu.dbc.robserver.message.entity.ChatFriend;
import com.xjtu.dbc.robserver.message.entity.MessageDto;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:29
 */
public interface MessageDao {
    List<ChatFriend> getChatFriendList(Integer userId);
    void startChat(Integer myId,Integer friendId);
    void updateUnRead(Integer myId,Integer friendId);
    void insertMessage(MessageDto messageDto);
    List<MessageDto> getMessageHistory(Integer myId,Integer friendId);
}
