package com.xjtu.dbc.robserver.dynamic.comment;

import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;

import java.util.List;

public interface DynamicCommentService {

    /**
     * 根据动态的编号获取动态的所有的评论
     * @param articleid 动态的编号
     * @return 动态的所有的评论的列表
     */
    List<DynamicCommentDto> getDynamicCommentList(Integer articleid);

}
