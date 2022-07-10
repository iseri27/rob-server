package com.xjtu.dbc.robserver.dynamic.vote.impl;


import com.xjtu.dbc.robserver.dynamic.report.dao.DynamicReportDao;
import com.xjtu.dbc.robserver.dynamic.report.entity.DynamicReportDto;
import com.xjtu.dbc.robserver.dynamic.vote.DynamicVoteService;
import com.xjtu.dbc.robserver.dynamic.vote.dao.DynamicVoteDao;
import com.xjtu.dbc.robserver.dynamic.vote.entity.DynamicVoteDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DynamicVoteServiceImpl implements DynamicVoteService {

    @Resource
    private DynamicVoteDao dynamicVoteDao;

    /**
     * 新增赞踩记录
     * @param dynamicVoteDto {用户编号, 文本编号, 赞踩类型}
     */
    @Override
    public  void doVote(DynamicVoteDto dynamicVoteDto) {
        dynamicVoteDao.doVote(dynamicVoteDto);
    }

    /**
     * 删除赞踩记录
     * @param dynamicVoteDto {用户编号, 文本编号, 赞踩类型}
     */
    @Override
    public  void deleteVote(DynamicVoteDto dynamicVoteDto) {
        dynamicVoteDao.deleteVote(dynamicVoteDto);
    }




}
