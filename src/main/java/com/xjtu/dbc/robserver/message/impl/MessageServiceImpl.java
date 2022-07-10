package com.xjtu.dbc.robserver.message.impl;

import com.xjtu.dbc.robserver.message.MessageService;
import com.xjtu.dbc.robserver.message.dao.MessageDao;
import com.xjtu.dbc.robserver.message.entity.ChatFriend;
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
    public List<ChatFriend> getChatFriends(int userId) {
        return messageDao.getChatFriendList(userId);
    }

    @Override
    public void startChat(int myId, int friendId) {
        messageDao.startChat(myId,friendId);
    }
}
