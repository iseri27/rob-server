package com.xjtu.dbc.robserver.question.comment;



import com.xjtu.dbc.robserver.question.comment.entity.CommentDto;

import java.util.List;

public interface CommentService {

    /**
     * 根据动态的编号获取动态的所有的评论
     * @param articleid 动态的编号
     * @return 动态的所有的评论的列表
     */
    List<CommentDto> getCommentList(Integer articleid);

    /**
     * 新增评论
     * @param commentDto {评论内容, 作者编号, 被回复这编号, 回复根节点编号}
     */
    void addComment(CommentDto commentDto);

    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    Integer getMaxCommentId();

}
