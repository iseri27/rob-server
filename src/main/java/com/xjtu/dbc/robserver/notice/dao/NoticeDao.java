package com.xjtu.dbc.robserver.notice.dao;

import com.xjtu.dbc.robserver.common.model.message.Message;
import com.xjtu.dbc.robserver.notice.entity.Notice;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/13 19:52
 */
public interface NoticeDao {
    List<Notice> getNoticeByType(Integer userId, Integer type);
    void updateUnReadNum(Integer userId,Integer type);
    void sendNoticeByArticle(Message message);
    void sendNoticeByReceiver(Message message);
}
