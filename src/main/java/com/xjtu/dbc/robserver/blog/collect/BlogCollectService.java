package com.xjtu.dbc.robserver.blog.collect;

public interface BlogCollectService {
    Integer addCollect(BlogCollectDto dto);

    void deCollect(BlogCollectDto dto);

    int getBookmarkid(BlogCollectDto dto);

    int getCollect(BlogCollectDto dto);
}
