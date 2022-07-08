package com.xjtu.dbc.robserver.manage.dao;

import com.xjtu.dbc.robserver.common.model.tag.Tag;

import java.util.List;

public interface ManageDao {
    List<Tag> selectTags(Tag tag);
}
