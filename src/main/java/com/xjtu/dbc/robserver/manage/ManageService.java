package com.xjtu.dbc.robserver.manage;

import com.xjtu.dbc.robserver.manage.entity.TagDto;

import java.util.Map;

public interface ManageService {
    Map<String, Object> getTagList(TagDto tag);
}
