package com.xjtu.dbc.robserver.question.comment.impl;



import com.xjtu.dbc.robserver.question.comment.CommentService;
import com.xjtu.dbc.robserver.question.comment.dao.CommentDao;
import com.xjtu.dbc.robserver.question.comment.entity.CommentDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public List<CommentDto> getCommentList(Integer articleid){
        return commentDao.getCommentList(articleid);
    }

    /**
     * 新增评论
     * @param commentDto {评论内容, 作者编号, 被回复这编号, 回复根节点编号}
     */
    @Override
    public  void addComment(CommentDto commentDto) {
        commentDao.addComment(commentDto);
    }



    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    @Override
    public Integer getMaxCommentId() {
        return commentDao.getMaxCommentId();
    }

    @Override
    public Boolean is_in_blacklist(Integer authorid, Integer articleid) {
        if( commentDao.is_in_blacklist(authorid,articleid) >=1){
            return  true;
        }
        else{
            return false;
        }
    }
}
