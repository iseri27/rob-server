package com.xjtu.dbc.robserver.dynamic.comment.impl;

import com.xjtu.dbc.robserver.dynamic.comment.DynamicCommentService;
import com.xjtu.dbc.robserver.dynamic.comment.dao.DynamicCommentDao;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.home.dao.DynamicHomeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DynamicCommentImpl implements DynamicCommentService {

    @Resource
    private DynamicCommentDao dynamicCommentDao;

    @Override
    public List<DynamicCommentDto> getDynamicCommentList(Integer articleid){
        return dynamicCommentDao.getDynamicCommentList(articleid);
    }
}
