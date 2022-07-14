package com.xjtu.dbc.robserver.notice.impl;

import com.xjtu.dbc.robserver.common.model.message.Message;
import com.xjtu.dbc.robserver.notice.NoticeService;
import com.xjtu.dbc.robserver.notice.dao.NoticeDao;
import com.xjtu.dbc.robserver.notice.entity.Notice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/13 19:52
 */
@Transactional@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    NoticeDao noticeDao;

    @Override
    public List<Notice> getNoticeByType(Integer userId, Integer type) {
        noticeDao.updateUnReadNum(userId, type);//更新已读状态
        return noticeDao.getNoticeByType(userId, type);//获得通知列表
    }

    @Override
    public void sendNotice(Message message) {
        noticeDao.sendNotice(message);
    }
}
