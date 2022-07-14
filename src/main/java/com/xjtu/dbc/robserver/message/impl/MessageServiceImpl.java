package com.xjtu.dbc.robserver.message.impl;

import com.xjtu.dbc.robserver.message.MessageService;
import com.xjtu.dbc.robserver.message.dao.MessageDao;
import com.xjtu.dbc.robserver.message.entity.ChatFriend;
import com.xjtu.dbc.robserver.message.entity.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/10 16:12
 */
@Transactional
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageDao messageDao;
    @Override
    public List<ChatFriend> getChatFriends(Integer userId) {
        return messageDao.getChatFriendList(userId);
    }

    @Override
    public void buildChat(Integer  myId,Integer friendId) {
        messageDao.startChat(myId,friendId);
    }

    @Override
    public void updateUnRead(Integer myId, Integer friendId) {
        messageDao.updateUnRead(myId,friendId);
    }

    @Override
    public List<MessageDto> getMessageHistory(Integer myId, Integer friendId) {
        return messageDao.getMessageHistory(myId, friendId);
    }
}
