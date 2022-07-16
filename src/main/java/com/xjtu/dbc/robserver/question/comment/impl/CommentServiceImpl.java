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

    /**
     * 根据动态的编号获取动态的所有的评论
     * @param articleid 动态的编号
     * @return 动态的所有的评论的列表
     */
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

    /**
     * 根据用户的编号与动态的编号来获取该动态的点赞点踩类型
     * @param authorid,aticleid  用户编编号与动态的编号
     * @return 该动态的点赞点踩类型
     */
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
