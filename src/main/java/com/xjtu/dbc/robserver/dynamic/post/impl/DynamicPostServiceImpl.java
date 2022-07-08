package com.xjtu.dbc.robserver.dynamic.post.impl;

import com.xjtu.dbc.robserver.dynamic.comment.dao.DynamicCommentDao;
import com.xjtu.dbc.robserver.dynamic.comment.entity.DynamicCommentDto;
import com.xjtu.dbc.robserver.dynamic.post.DynamicPostService;
import com.xjtu.dbc.robserver.dynamic.post.dao.DynamicPostDao;
import com.xjtu.dbc.robserver.dynamic.post.entity.DynamicPostDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class DynamicPostServiceImpl implements DynamicPostService {

    @Resource
    private DynamicPostDao dynamicPostDao;



    /**
     * 新增动态
     * @param dynamicPostDto {动态内容, 作者编号}
     */
    @Override
    public  void addDynamic(DynamicPostDto dynamicPostDto) {
        dynamicPostDao.addDynamic(dynamicPostDto);
    }



    /**
     * 获取当前最大的动态记录的编号
     * @return 最大的动态记录的编号
     */
    @Override
    public Integer getMaxDynamicId() {
        return dynamicPostDao.getMaxDynamicId();
    }

}
