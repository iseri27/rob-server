package com.xjtu.dbc.robserver.dynamic.comment.dao;

import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.entity.DynamicHomeListDto;

import java.util.List;

public interface DynamicCommentDao {

    List<DynamicCommentDto> getDynamicCommentList(Integer articleid);

}
