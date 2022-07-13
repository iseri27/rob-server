package com.xjtu.dbc.robserver.blog.collect.impl;

import com.xjtu.dbc.robserver.blog.collect.BlogCollectDto;
import com.xjtu.dbc.robserver.blog.collect.BlogCollectService;
import com.xjtu.dbc.robserver.blog.collect.dao.BlogCollectDao;
import com.xjtu.dbc.robserver.common.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BlogCollectServiceImpl implements BlogCollectService {
    @Resource
    private BlogCollectDao blogCollectDao;

    @Override
    public Integer addCollect(BlogCollectDto dto){
        int hadCollected=blogCollectDao.hadCollectedOrNot(dto);
        //已经收藏过
        if(hadCollected > 0){
            deCollect(dto);
            return 0;
        }
        //之前未收藏
        else{
            dto.setCreatetime(Utils.getNow());
            blogCollectDao.addCollect(dto);
            return 1;
        }
    }

    @Override
    public void deCollect(BlogCollectDto dto){
        blogCollectDao.deCollect(dto);
    }

    @Override
    public int getBookmarkid(BlogCollectDto dto){
        return blogCollectDao.selectBookmarkid(dto);
    }

    @Override
    public int getCollect(BlogCollectDto dto){
        return blogCollectDao.hadCollectedOrNot(dto);
    }
}
