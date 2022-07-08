package com.xjtu.dbc.robserver.blog.collect.dao;

import com.xjtu.dbc.robserver.blog.collect.BlogCollectDto;

public interface BlogCollectDao {
    int hadCollectedOrNot(BlogCollectDto dto);

    void addCollect(BlogCollectDto dto);

    void deCollect(BlogCollectDto dto);

    int selectBookmarkid(BlogCollectDto dto);
}
