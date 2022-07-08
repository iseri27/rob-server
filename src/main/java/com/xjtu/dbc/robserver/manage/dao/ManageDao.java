package com.xjtu.dbc.robserver.manage.dao;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.entity.TagDto;

import java.util.List;

public interface ManageDao {
    List<Tag> selectTags(TagDto tagDto);
}
