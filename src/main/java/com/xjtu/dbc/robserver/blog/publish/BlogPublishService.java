package com.xjtu.dbc.robserver.blog.publish;

import java.util.Map;

public interface BlogPublishService {
    Map<String, Object> getBlogList(BlogDto dto);
}
