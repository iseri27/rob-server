package com.xjtu.dbc.robserver.manage;

import com.xjtu.dbc.robserver.common.model.tag.Tag;

import java.util.Map;

public interface ManageService {
    Map<String, Object> getTagList(Tag tag);
}
