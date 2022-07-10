package com.xjtu.dbc.robserver.dynamic.vote;

import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.dynamic.vote.entity.DynamicVoteDto;

public interface DynamicVoteService {

    /**
     * 新增赞踩记录
     * @param dynamicVoteDto {用户编号, 文本编号, 赞踩类型}
     */
    void doVote(DynamicVoteDto dynamicVoteDto);

    /**
     * 删除赞踩记录
     * @param dynamicVoteDto {用户编号, 文本编号, 赞踩类型}
     */
    void deleteVote(DynamicVoteDto dynamicVoteDto);
}
