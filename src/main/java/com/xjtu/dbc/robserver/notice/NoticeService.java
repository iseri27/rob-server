package com.xjtu.dbc.robserver.notice;

import com.xjtu.dbc.robserver.common.model.message.Message;
import com.xjtu.dbc.robserver.notice.entity.Notice;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/13 19:51
 */
public interface NoticeService {
    List<Notice> getNoticeByType(Integer userId, Integer type);
    void sendNotice(Message message);
}
