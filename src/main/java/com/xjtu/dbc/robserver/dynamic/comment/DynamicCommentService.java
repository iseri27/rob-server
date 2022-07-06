package com.xjtu.dbc.robserver.dynamic.comment;

import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;

import java.util.List;

public interface DynamicCommentService {

    /**
     * 根据动态的编号获取动态的所有的评论
     * @param articleid 动态的编号
     * @return 动态的所有的评论的列表
     */
    List<DynamicCommentDto> getDynamicCommentList(Integer articleid);

    /**
     * 新增评论
     * @param dynamicCommentDto {评论内容, 作者编号, 被回复这编号, 回复根节点编号}
     */
    void addComment(DynamicCommentDto dynamicCommentDto);

    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    Integer getMaxCommentId();

}
