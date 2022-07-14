package com.xjtu.dbc.robserver.blog.reply.impl;

import com.xjtu.dbc.robserver.blog.reply.BlogReplyService;
import com.xjtu.dbc.robserver.blog.reply.ReplyDto;
import com.xjtu.dbc.robserver.blog.reply.dao.BlogReplyDao;
import com.xjtu.dbc.robserver.common.model.reply.Reply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service @Transactional
public class BlogReplyServiceImpl implements BlogReplyService {
    @Resource
    private BlogReplyDao replyDao;

    @Override
    public void reply(Reply dto){
        replyDao.insertReply(dto);
    }

    @Override
    public List<ReplyDto> getReplyList(int articleid){
        List<ReplyDto> replyDtoList = replyDao.getReplyList(articleid);
        for (ReplyDto reply: replyDtoList) {
            reply.setReplytouserid(replyDao.getReplytouserid(reply.getReplyto()));
        }
        return replyDtoList;
    }

    @Override
    public Integer ifPullBlack(Integer myid, Integer replyto) {
        return replyDao.ifPullBlack(myid, replyto);
    }

    @Override
    public boolean cannotDelReply(Reply dto) {
        if(replyDao.isMyArticle(dto.getReplyid(),dto.getAuthorid()) > 0){
            return false;
        }
        if(replyDao.isMyArticle(dto.getReplyto(),dto.getAuthorid()) > 0){
            return false;
        }
        if(replyDao.isMyArticle(dto.getRootid(),dto.getAuthorid()) > 0){
            return false;
        }
        return true;
    }

    @Override
    public void delReply(Integer replyid) {
        replyDao.delReply(replyid);
    }

}
