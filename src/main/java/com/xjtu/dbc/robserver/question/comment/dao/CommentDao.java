package com.xjtu.dbc.robserver.question.comment.dao;

import com.xjtu.dbc.robserver.question.comment.entity.CommentDto;

import java.util.List;

public interface CommentDao {
    /**
     * 获取评论列表
     * @param articleid 评论id
     */
    List<CommentDto> getCommentList(Integer articleid);

    /**
     * 新增评论
     * @param commentDto 评论的信息
     */
    void addComment(CommentDto commentDto);

    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    Integer getMaxCommentId();
}
