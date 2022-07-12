package com.xjtu.dbc.robserver.manage.tag.dao;

import com.xjtu.dbc.robserver.common.model.tag.Tag;
import com.xjtu.dbc.robserver.manage.tag.entity.TagDto;

import java.util.List;

/**
 * @author yjq
 * @version 1.0
 * @date 2022/7/12 8:50
 */
public interface TagDao {
    List<Tag> selectTags(TagDto tagDto);

    void insertTag(Tag tag);

    void deleteTag(Tag tag);

    void updateTag(Tag tag);

    void deleteRelation(Integer tagid);
}
