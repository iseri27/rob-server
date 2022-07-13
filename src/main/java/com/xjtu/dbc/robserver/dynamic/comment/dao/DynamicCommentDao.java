package com.xjtu.dbc.robserver.dynamic.comment.dao;

import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;

import java.util.List;

public interface DynamicCommentDao {

    List<DynamicCommentDto> getDynamicCommentList(Integer articleid);



    Integer is_in_blacklist(Integer authorid,Integer articleid);

    /**
     * 新增评论
     * @param dynamicCommentDto 评论的信息
     */
    void addComment(DynamicCommentDto dynamicCommentDto);

    /**
     * 获取当前最大的评论记录的编号
     * @return 最大的评论记录的编号
     */
    Integer getMaxCommentId();

}
