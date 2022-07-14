package com.xjtu.dbc.robserver.message;

import com.xjtu.dbc.robserver.message.entity.ChatFriend;
import com.xjtu.dbc.robserver.message.entity.MessageDto;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:11
 */
public interface MessageService {
    List<ChatFriend> getChatFriends(Integer userId);
    void buildChat(Integer myId,Integer friendId);
    void updateUnRead(Integer myId,Integer friendId);
    List<MessageDto> getMessageHistory(Integer myId, Integer friendId);
}
